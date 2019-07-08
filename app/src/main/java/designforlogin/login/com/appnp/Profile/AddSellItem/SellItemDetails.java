package designforlogin.login.com.appnp.Profile.AddSellItem;


import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import designforlogin.login.com.appnp.LoginActivity;
import designforlogin.login.com.appnp.MainActivity;
import designforlogin.login.com.appnp.R;
import designforlogin.login.com.appnp.StaticVariable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellItemDetails extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private String TAG = "testImage";
    Context mContext;
    List<String> categoryList;
    ArrayAdapter<String> dataAdapter;
    EditText txtName, txtModel, txtPrice, txtCondition, txtPriceStatus;
    Button btnAddDetails;
    ImageView detailImage1, detailImage2, detailImage3;
    String itemCategory;
    Bitmap bitmap1, bitmap2, bitmap3;
    private int REQUEST_CODE_IMAGE = 771;


    public SellItemDetails() {
        // Required empty public constructor
    }

    List<Uri> listImage;

    Spinner spinSelectCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sell_item_details, container, false);
        spinSelectCategory = view.findViewById(R.id.spinSelectCategory);
        categoryList = new ArrayList<>();
        txtName = view.findViewById(R.id.txtName);
        txtModel = view.findViewById(R.id.txtModel);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtCondition = view.findViewById(R.id.txtCondition);
        txtPriceStatus = view.findViewById(R.id.txtPriceStatus);
        btnAddDetails = view.findViewById(R.id.btnAddDetails);
        detailImage1 = view.findViewById(R.id.detailImage1);
        detailImage2 = view.findViewById(R.id.detailImage2);
        detailImage3 = view.findViewById(R.id.detailImage3);


        listImage = new ArrayList<>();


        //init list adapter
        //download data in list via network call; check: data is in list
        initAdapter();

        fetchCategoryList(); //todo undo
        Log.i("netRequest", "onCreateView: category size " + categoryList.size());

        detailImage2.setVisibility(View.GONE);
        detailImage3.setVisibility(View.GONE);
        spinSelectCategory.setOnItemSelectedListener(this);
        btnAddDetails.setOnClickListener(this);
        detailImage1.setOnClickListener(this);
        detailImage2.setOnClickListener(this);
        detailImage3.setOnClickListener(this);


        return view;
    }

    private void initAdapter() {
        dataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, categoryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSelectCategory.setAdapter(dataAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public void fetchCategoryList() {

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(StaticVariable.AddItemCategoryUrl)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                //call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {
                    String mMessage = response.body().string();
                    Log.d("refreshCheck", "mystirng" + mMessage);

                    JSONArray jObj = new JSONArray(mMessage);
                    Log.d("refreshCheck", "onResponsejjjjj: " + jObj.toString());

                    for (int i = 0; i < jObj.length(); i++) {
                        //eg len = 4
                        //condition
                        // 1. is i less than array length
                        // 2. is i equal to array length
                        JSONObject myString = jObj.getJSONObject(i);
                        String a = myString.getString("Category");
                        categoryList.add(a);
                        Log.i("refreshCheck", "onResponse: looping");

                        if (i == jObj.length() - 1) {
                            refreshInUIThread();
                            Log.i("refreshCheck", "onResponse: refreshInUIThread");

                        }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });
        //set the downloaded data to the adapter


    }

    private void refreshInUIThread() {
// Get a handler that can be used to post to the main thread
        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                dataAdapter.notifyDataSetChanged();
                Log.d("refreshCheck", "run:fffff ");
            } // This is your code
        };
        mainHandler.post(myRunnable);

//        new Runnable() {
//            @Override
//            public void run() {
//                dataAdapter.notifyDataSetChanged();
//                Log.d("refreshCheck", "run:fffff ");
//            }
//        }.run();


    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("selectionTest", String.valueOf(i));
        itemCategory = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void postDetailsToServer() {
        permission();
        changeToBitmap();
        encodedImageBitmap();
        new DoUpload().execute();

    }

    private void permission() {

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext, "Permission Denied", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 77
                );

//            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},);
            }

        } else {

        }
    }


    public void chooseItemImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 771) {
            Uri chosenImageUri = data.getData();
            listImage.add(chosenImageUri);
            Log.d("bitmap", "Mybitmap: " + chosenImageUri);
            changeToBitmap();

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddDetails: {
                postDetailsToServer();

                return;
            }
            case R.id.detailImage1: {
                chooseItemImage();
                return;
            }
            case R.id.detailImage2: {
                chooseItemImage();

                return;
            }
            case R.id.detailImage3: {
                chooseItemImage();

            }

        }

    }

    public void changeToBitmap() {
        if (listImage.size() == 1) {

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), listImage.get(0));
                detailImage1.setImageBitmap(bitmap1);
                detailImage2.setVisibility(View.VISIBLE);


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (listImage.size() == 2) {

            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), listImage.get(1));
                detailImage2.setImageBitmap(bitmap2);
                detailImage3.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (listImage.size() == 3) {

            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), listImage.get(2));
                detailImage3.setImageBitmap(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void encodedImageBitmap() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String encodedImage2, encodedImage3;
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes1 = baos.toByteArray();
        final String encodedImage1 = Base64.encodeToString(imageBytes1, Base64.DEFAULT);


        if (bitmap2 != null) {
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
            byte[] imageBytes2 = baos2.toByteArray();
            encodedImage2 = Base64.encodeToString(imageBytes2, Base64.DEFAULT);

            Log.d("string bitmap", "BitmappostDetailsToServer:" + encodedImage2);
        } else {
            encodedImage2 = "";
        }
        if (bitmap3 != null) {
            ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
            bitmap3.compress(Bitmap.CompressFormat.JPEG, 100, baos3);
            byte[] imageBytes3 = baos3.toByteArray();
            encodedImage3 = Base64.encodeToString(imageBytes3, Base64.DEFAULT);
        } else {
            encodedImage3 = "";
        }
    }

    public byte[] bitMapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    class DoUpload extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            final String name = txtName.getText().toString().trim();
            final String model = txtModel.getText().toString().trim();
            final String condition = txtCondition.getText().toString().trim();
            final String price = txtPrice.getText().toString().trim();
            final String priceStatus = txtPriceStatus.getText().toString().trim();

            OkHttpClient client = new OkHttpClient();
            final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpeg");
            File mySecondimage = null, myThirdimage = null;
            try {
                List<File> fileList = new ArrayList<>();
                for (int i = 0; i < listImage.size(); i++) {
                    fileList.add(new File(getFilePath(mContext, listImage.get(i))));
                }

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Response result;

            MultipartBody.Builder buildernew = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("tags", "tag")  //Here you can add the fix number of data.
                    .addFormDataPart("name", name)//Here you can add the fix number of data.
                    .addFormDataPart("model", model)//Here you can add the fix number of data.
                    .addFormDataPart("condition", condition)  //Here you can add the fix number of data.
                    .addFormDataPart("price", price)  //Here you can add the fix number of data.
                    .addFormDataPart("category", itemCategory)  //Here you can add the fix number of data.
                    .addFormDataPart("priceStatus", priceStatus);   //Here you can add the fix number of data.

            for (int i = 0; i < listImage.size(); i++) {
                File f = null;
                try {
                    f = new File(getFilePath(mContext, listImage.get(i)));
                    Log.d(TAG, "doBAck: " + getFilePath(mContext, listImage.get(i)));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                if (f.exists()) {
                    long imagename = System.currentTimeMillis();
                    buildernew.addFormDataPart("pic" + "[" + i + "]", imagename + i + ".png", RequestBody.create(MEDIA_TYPE_JPG, f));
                }
            }


            MultipartBody requestBody = buildernew.build();
            Request request = new Request.Builder()
                    .url(StaticVariable.AddItemDetailUrl)
                    .post(requestBody)
                    .build();
            try {

                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {

//                    Toast.makeText(mContext, "Successfully posted!", Toast.LENGTH_SHORT).show();
                }
              /*  Log.i("RESPONSSE", response.body().string().trim());

                String a = response.body().string().trim();
                Log.i("yyy", "doInBackground: "+a);

                JSONObject j = new JSONObject(a);
                String success = j.getString("message");
                Log.i("RESPONSSEString", success);*/
//
//                if (success.equals("success")) {
//                    Intent intent = new Intent(mContext, MainActivity.class);
//                    startActivity(intent);
//                }

//                if(response.body().string().trim().equals("success")){
//                    Log.i("RESPONSSE", response.body().string().trim());
//
//                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, "doInBackground: " + e);
            }/* catch (JSONException e) {
                e.printStackTrace();
            }
        */


//                    RequestBody reques = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .addFormDataPart("tags", "tags")
//                            .addFormDataPart("pics", imagename + "first" + ".png",
//                                    RequestBody.create(MEDIA_TYPE_JPG, ))
//                            .build();
//
//
//                    try {
//
//                        Response response = client.newCall(request).execute();
//                        Log.i("RESPONSSE", response.body().string());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }


            return null;

        }


        public String getFilePath(Context context, Uri uri) throws URISyntaxException {
            String selection = null;
            String[] selectionArgs = null;
            // Uri is different in versions after KITKAT (Android 4.4), we need to
            if (DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                } else if (isDownloadsDocument(uri)) {
                    final String id = DocumentsContract.getDocumentId(uri);
                    uri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                } else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    if ("image".equals(type)) {
                        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    selection = "_id=?";
                    selectionArgs = new String[]{
                            split[1]
                    };
                }
            }
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                String[] projection = {
                        MediaStore.Images.Media.DATA
                };
                Cursor cursor = null;
                try {
                    cursor = context.getContentResolver()
                            .query(uri, projection, selection, selectionArgs, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index);
                    }
                } catch (Exception e) {
                }
            } else if ("fileUri".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        }

        public boolean isExternalStorageDocument(Uri uri) {
            return "com.android.externalstorage.documents".equals(uri.getAuthority());
        }

        public boolean isDownloadsDocument(Uri uri) {
            return "com.android.providers.downloads.documents".equals(uri.getAuthority());
        }

        public boolean isMediaDocument(Uri uri) {
            return "com.android.providers.media.documents".equals(uri.getAuthority());
        }
        ///////////////////////////----------------------------------------------------------------------------------------------
    }
}


