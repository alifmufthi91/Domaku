package com.example.kienz.domaku.donasi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kienz.domaku.MainActivity;
import com.example.kienz.domaku.R;
import com.example.kienz.domaku.donasi_doneActivity;
import com.example.kienz.domaku.model.donasi;
import com.example.kienz.domaku.model.koordinat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link donasi_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link donasi_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class donasi_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.donasi_upload_img)
    ImageView uploadImage;
    @BindView(R.id.donasi_buat)
    Button buatButton;
    @BindView(R.id.donasi_judul)
    EditText judulEditText;
    @BindView(R.id.donasi_jumlah)
    EditText jumlahEditText;
    @BindView(R.id.donasi_alamat)
    EditText alamatEditText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.donasi_upload_btn)
    Button uploadBtn;
    @BindView(R.id.donasi_bingkai)
    ConstraintLayout bingkaiImage;
    Bitmap bitmap;
    byte[] ImageReady;
    String urlImage;

    private StorageReference storageRef;
    private Uri filePath;


    private final int PICK_IMAGE_REQUEST = 71;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity Activity;
    private FirebaseAuth mAuth;


    private OnFragmentInteractionListener mListener;

    public donasi_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment donasi_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static donasi_frag newInstance(String param1, String param2) {
        donasi_frag fragment = new donasi_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Activity = (MainActivity) getActivity();
        mAuth = FirebaseAuth.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_donasi, container, false);
        ButterKnife.bind(this,v);
        jumlahEditText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        jumlahEditText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        jumlahEditText.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (jumlahEditText.getText().toString().matches("^0") )
                {
                    // Not allowed
                    Toast.makeText(getContext(), "Tidak Boleh kosong", Toast.LENGTH_LONG).show();
                    jumlahEditText.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
        buatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getView().clearFocus();
                showProgressDialog();
                if(jumlahEditText.getText().toString().equals("")){
                    jumlahEditText.setError("Jumlah tidak boleh kosong");
                    hideProgressDialog();
                }else if(judulEditText.getText().toString().equals("")) {
                    judulEditText.setError("Judul tidak boleh kosong");
                    hideProgressDialog();
                }else if(alamatEditText.getText().toString().equals("")){
                    alamatEditText.setError("Judul tidak boleh kosong");
                    hideProgressDialog();
                }else if(filePath==null) {
                    Toast.makeText(getContext(),"Berikan foto untuk makanan",Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                }else{
                    if(Integer.parseInt(jumlahEditText.getText().toString())<1){
                        jumlahEditText.setError("Jumlah tidak boleh kurang dari 1");
                    }else{
                        String userId = mAuth.getCurrentUser().getUid();
                        final StorageReference imageDonasi = storageRef.child("images/"+userId+"/"+UUID.randomUUID()+".jpg");
                        final UploadTask uploadTask = imageDonasi.putBytes(ImageReady);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                uploadTask.cancel();
                                hideProgressDialog();
                                // Handle unsuccessful uploads
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                                imageDonasi.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        try {
                                            urlImage = new URL(uri.toString()).toString();
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        }
//                                    donasi donasiBaru = new donasi(judulEditText.getText().toString(),Integer.parseInt(jumlahEditText.getText().toString()),urlImage,alamatEditText.getText().toString(),mAuth.getCurrentUser().getUid());
                                        //TODO: Buat constructor baru ketika sudah selesai
                                        donasi donasiBaru = new donasi();
                                        donasiBaru.setJudul(judulEditText.getText().toString());
                                        donasiBaru.setAlamat(alamatEditText.getText().toString());
                                        donasiBaru.setQty(Integer.parseInt(jumlahEditText.getText().toString()));
                                        donasiBaru.setGambar(urlImage);
                                        donasiBaru.setDonatur(mAuth.getCurrentUser().getUid());
                                        donasiBaru.setKoordinattempat(new koordinat(-6.6232204,107.17919));
                                        donasiBaru.setSisa(donasiBaru.getQty());
                                        Activity.mDonasiDatabaseReference.push().setValue(donasiBaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getContext(),"Donasi telah dibuat",Toast.LENGTH_SHORT).show();
                                                hideProgressDialog();
                                                Intent afterDonasi = new Intent(getActivity(),donasi_doneActivity.class);
                                                startActivity(afterDonasi);
                                                clearField();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }

                }

            }
        });
        progressBar.setIndeterminate(true);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void clearField(){
        judulEditText.clearFocus();
        judulEditText.setText("");
        jumlahEditText.clearFocus();
        jumlahEditText.setText("");
        alamatEditText.clearFocus();
        alamatEditText.setText("");
        uploadImage.setImageResource(R.drawable.defaultthumbs);
        filePath = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap = Bitmap.createScaledBitmap(bitmap,bingkaiImage.getWidth(),bingkaiImage.getMinHeight(),true);
                bitmap = scaleRatio(bitmap,600,600);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                ImageReady = baos.toByteArray();
                uploadImage.setImageDrawable(d);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressDialog() {
        progressBar.setVisibility(View.GONE);

    }

    public Bitmap scaleRatio(Bitmap originalImage, float width, float height){
        Bitmap background = Bitmap.createBitmap((int)width, (int)height, Bitmap.Config.ARGB_8888);
        float originalWidth = originalImage.getWidth();
        float originalHeight = originalImage.getHeight();

        Canvas canvas = new Canvas(background);
        float scale = width / originalWidth;

        float xTranslation = 0.0f;
        float yTranslation = (height - originalHeight * scale) / 2.0f;

        Matrix transformation = new Matrix();
        transformation.postTranslate(xTranslation, yTranslation);
        transformation.preScale(scale, scale);

        Paint paint = new Paint();
        paint.setFilterBitmap(true);

        canvas.drawBitmap(originalImage, transformation, paint);

        return background;
    }


    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }
}
