package EachSingle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.everything.R;

public class PhoneCallActivity extends AppCompatActivity
{
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        mEditTextNumber = findViewById(R.id.edit_text_number);
        ImageView imageCall = findViewById(R.id.image_call);

        imageCall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall()
    {
        String number = mEditTextNumber.getText().toString();

        //.trim() means it will automatically remove all the spaces between numbers
        if (number.trim().length() > 0)
        {
            if (ContextCompat.checkSelfPermission(PhoneCallActivity.this , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                //IF user has not given the permission
                ActivityCompat.requestPermissions(PhoneCallActivity.this , new String[] {Manifest.permission.CALL_PHONE} , REQUEST_CALL);

            }
            else
            {
                //If user has given the permission
                String dial = "tel:" +number;
                startActivity(new Intent(Intent.ACTION_CALL , Uri.parse(dial)));

            }

        }
        else
        {
            Toast.makeText(this, "Enter the Mobile Number", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
            else
            {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}