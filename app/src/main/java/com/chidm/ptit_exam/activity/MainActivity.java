package com.chidm.ptit_exam.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chidm.ptit_exam.R;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.helper.ImageHelper;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import com.chidm.ptit_exam.task.training.AddFaceTask;
import com.chidm.ptit_exam.task.training.AddPersonGroupTask;
import com.chidm.ptit_exam.task.training.AddPersonTask;
import com.chidm.ptit_exam.task.training.DetectionTask;
import com.chidm.ptit_exam.task.training.IdentificationTask;
import com.chidm.ptit_exam.task.training.TrainPersonGroupTask;
import com.chidm.ptit_exam.task.validation.StudentUpdator;
import com.chidm.ptit_exam.task.validation.StudentValidation;
import com.chidm.ptit_exam.utils.EndPoint;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.Face;
import com.microsoft.projectoxford.face.contract.FaceRectangle;
import com.microsoft.projectoxford.face.contract.IdentifyResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.support.constraint.Constraints.TAG;

public class MainActivity extends AppCompatActivity
    implements OnTaskCompleted, View.OnClickListener {
    private FaceServiceRestClient faceServiceClient =
        new FaceServiceRestClient(Constant.API_ENDPOINT, Constant.API_KEY);
    private ImageView imvInput, imvOutput;
    private Button btGallery, btCamera;
    private List<String> personGroupIdList;
    private String personGroupId, personId, trainingId;
    private Face[] detectionResult1, detectionResult2;
    private IdentifyResult[] identifyResults;
    private Map<String, String> personMap = new HashMap<>();
    public List<FaceRectangle> faceRectList = new ArrayList<>();
    public Bitmap mBitmap;
    public Uri mImageUri;
    public Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //    new GroupSubjectGetter(this, new EndPoint<List<String>>(), "101A2").execute();
//        getPersonGroup();
        new StudentValidation(this, new EndPoint<Boolean>())
            .execute("f3c37004-2745-4daf-ae1c-1dcebafe3703", "GR1", "101A2");
    }

    private void initView() {
        imvInput = findViewById(R.id.imv_input);
        imvOutput = findViewById(R.id.imv_output);
        btGallery = findViewById(R.id.bt_gallery);
        btCamera = findViewById(R.id.bt_camera);
        personGroupIdList = new ArrayList<>();
        btCamera.setOnClickListener(this);
        btGallery.setOnClickListener(this);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
        }
        startActivityForResult(intent, Constant.PICK_IMAGE);
    }

    private void openCamera() {
        String[] permission =
            new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ContextCompat.checkSelfPermission(this, permission[0]) !=
            PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, permission[1]) !=
            PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, permission[2]) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, permission, 1);
        } else {
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(pictureIntent,
                    Constant.REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 3
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED
            && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(pictureIntent, Constant.REQUEST_CAPTURE_IMAGE);
        }
    }

    void createGroup() {
        personGroupId = UUID.randomUUID().toString();
        Constant.PERSON_GROUP_ID = personGroupId;
        personGroupIdList.add(personGroupId);
        new AddPersonGroupTask(MainActivity.this, this).execute(personGroupId);
    }

    void getPersonGroup() {
        if (personGroupIdList.isEmpty()) {
            Log.d("MainActivity", "Creat Group");
            createGroup();
        } else {
            personGroupId = Constant.PERSON_GROUP_ID;
            Log.d("MainActivity", "Group" + personGroupId);
        }
    }

    void addPerson() {
        if (personId == null) {
            new AddPersonTask(MainActivity.this, this).execute(personGroupId);
        } else {
            Log.d(MainActivity.class.getSimpleName(), "Đã tồn tại personId " + personId);
        }
    }

    private static Bitmap drawFaceRectanglesOnBitmap(
        Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        if (faces != null) {
            for (Face face : faces) {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(
                    faceRectangle.left,
                    faceRectangle.top,
                    faceRectangle.left + faceRectangle.width,
                    faceRectangle.top + faceRectangle.height,
                    paint);
            }
        }
        return bitmap;
    }

    private void detect(Bitmap bitmap) {
        // Put the image into an input stream for detection.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());
        new DetectionTask(this, this).execute(inputStream);
    }

    public void identify(List<Face> listFace) {
        // Start detection task only if the image to detect is selected.
        //    if (detected && mPersonGroupId != null) {
        // Start a background task to identify faces in the image.
        List<UUID> faceIds = new ArrayList<>();
        for (Face face : listFace) {
            faceIds.add(face.faceId);
        }
        new IdentificationTask(this, this, Constant.PERSON_GROUP_ID).execute(
            faceIds.toArray(new UUID[faceIds.size()]));
    }

    @Override
    public void handle(Object o) throws IOException, NetworkOnMainThreadException {
        switch (Constant.INDEX) {
            case 1:
                personGroupId = (String) o;
                personGroupIdList.add(personGroupId);
                Constant.PERSON_GROUP_ID = personGroupId;
                Log.d(MainActivity.class.getSimpleName(), "personGroupId = " + personGroupId);
                addPerson();
                break;
            case 2:
                String personId1 = (String) o;
                personId = personId1;
//                this.personMap.put("b14dccn096", personId);
                new StudentUpdator(this, new EndPoint<Boolean>())
                    .execute("b14dccn096", Constant.PERSON_GROUP_ID, personId);
                Log.d(MainActivity.class.getSimpleName(), "personId1 = " + personId1);
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //    in = new URL("https://res.cloudinary.com/daikk6zhp/image/upload/v1540635014/FaceGroup/b13dcat023/1.png").openStream();
                //    Log.d(MainActivity.class.getSimpleName(), "in = " + in.toString());
                mBitmap = ImageHelper.loadSizeLimitedBitmapFromUrl(
                    "https://res.cloudinary.com/daikk6zhp/image/upload/v1540635014/FaceGroup/b14dccn310/1.png");
                if (mBitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    InputStream imageInputStream = new ByteArrayInputStream(stream.toByteArray());
                    new DetectionTask(MainActivity.this, MainActivity.this)
                        .execute(imageInputStream);
                }
                break;
            case 3:
                detectionResult1 = (Face[]) o;
                if (detectionResult1 != null) {
                    List<Face> listFace = Arrays.asList(detectionResult1);
                    new AddFaceTask(MainActivity.this, MainActivity.this, personGroupId, personId,
                        mBitmap, listFace).execute();
                    Log.d(MainActivity.class.getSimpleName(), "AddFaceTask" + Constant.INDEX);
                }
                break;
            case 4:
                if (o.equals(Boolean.valueOf(true))) {
                    Log.d(MainActivity.class.getSimpleName(), "sucessfully");
                    new TrainPersonGroupTask(MainActivity.this, MainActivity.this)
                        .execute(personGroupId);
                } else Log.d(MainActivity.class.getSimpleName(), "fail");
                break;
            case 5:
                trainingId = (String) o;
                Log.d(MainActivity.class.getSimpleName(), "trainingId = " + trainingId);
                break;
            case 6: // detect ảnh lấy từ client (camera or memory)
                detectionResult2 = (Face[]) o;
                if (detectionResult2 != null) {
                    List<Face> listFaces = Arrays.asList(detectionResult2);
                    identify(listFaces);
                    Log.d(MainActivity.class.getSimpleName(), "detect success " + Constant.INDEX);
                } else {
                    Log.d(MainActivity.class.getSimpleName(), "detect fail " + Constant.INDEX);
                }
                break;
            case 7:
                identifyResults = (IdentifyResult[]) o;
                if (identifyResults != null) {
                    List<String> listPerId = new ArrayList<>();
                    List<IdentifyResult> listIdentifyResults = Arrays.asList(identifyResults);
                    for (IdentifyResult i : listIdentifyResults) {
                        DecimalFormat formatter = new DecimalFormat("#0.00");
                        if (i.candidates.size() > 0) {
                            String perId =
                                i.candidates.get(0).personId.toString();
                            listPerId.add(perId);
                            Log.d(MainActivity.class.getSimpleName(), "personId: " + perId);
                        }
                        Log.d(MainActivity.class.getSimpleName(),
                            "identify success" + Constant.INDEX);
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_camera:
                openCamera();
                break;
            case R.id.bt_gallery:
                openGallery();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            //    detected = false;
            mImageUri = data.getData();
            imageBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(
                mImageUri, this.getContentResolver());
            //    mImageDisplay.setImageBitmap(imageBitmap);
            detect(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        Constant.INDEX = 0;
        super.onDestroy();
    }
}
