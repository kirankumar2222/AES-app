package Main;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.Algorithms.R;

import java.security.PublicKey;

import Encryption.Algorithms.AES;

public class Old_MainActivity extends AppCompatActivity {
    private String message;
    private String key;
    private Button Switch;
    private Button Encrypt_Buuton;
    private Button Decrypt_Buuton;

    private TextView Answer;
    private EditText Textfield_Text;
    private EditText Textfield_Key;
    private TextView Matrix_value;
    private ConstraintLayout ConstraintLayout;
    private ConstraintSet ConstraintSet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.encryption_main);
        Switch = findViewById(R.id.Swtich);
        Encrypt_Buuton = findViewById(R.id.Encrypt_Buuton);
        Decrypt_Buuton = findViewById(R.id.Decrypt_Buuton);
        Answer = findViewById(R.id.Answer);
        Textfield_Text = findViewById(R.id.TextArea);
        Textfield_Key = findViewById(R.id.Key);
        Matrix_value = findViewById(R.id.Matrix);
        ConstraintLayout = findViewById(R.id.ConstraintLayout);
        ConstraintSet = new ConstraintSet();
        ConstraintSet.clone(ConstraintLayout);
    }

    public void Encrypt(View view) throws Exception {


        if (Textfield_Text.length() == 0) {
            Toast.makeText(this, "Enter a message to Encrypt", Toast.LENGTH_SHORT).show();
            return;
        }
        message = String.valueOf(Textfield_Text.getText());
        key = String.valueOf(Textfield_Key.getText());
        String Algorithm = String.valueOf(Switch.getText());
        switch (Algorithm) {
            case "Advanced Encryption Standard": {
                AES aes = new AES();
                String encData = aes.AESencrypt(key.getBytes("UTF-16LE"), message.getBytes("UTF-16LE"));
                Answer.setText(encData);
                break;
            }
            }


            //            case "RSA": {
//                try {
//
//                    keyPair = buildKeyPair();
//                    PrivateKey privateKey = keyPair.getPrivate();
//                    pubKey = keyPair.getPublic();
//                    byte[] signed = encryptRSA(privateKey, message);
//                    String stringToStore = new String(Base64.encode(signed, 0));
//                    answer.setText(stringToStore);
//
//                } catch (Exception e) {
//                    Toast.makeText(this, "Your message is to long", Toast.LENGTH_SHORT).show();
//
//                }
//                break;
//            }

        }


 //   public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
     //   final int keySize = 2048;
     //   KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      //  keyPairGenerator.initialize(keySize);
      //  return keyPairGenerator.genKeyPair();
    //}

    private static PublicKey pubKey;

    public void Decrypt(View view) throws Exception {
        if (Textfield_Text.length() == 0) {
            Toast.makeText(this, "Enter a message to Decrypt", Toast.LENGTH_SHORT).show();
            return;
        }

        message = String.valueOf(Textfield_Text.getText());
        key = String.valueOf(Textfield_Key.getText());
        String SwitchValue = Switch.getText().toString();
        switch (SwitchValue) {
            case "Advanced Encryption Standard":
                AES aes = new AES();
                try {
                    String decData = aes.AESdecrypt(key, Base64.decode(message.getBytes("UTF-16LE"), Base64.DEFAULT));
                    Answer.setText(decData);
                } catch (Exception e) {
                    Toast.makeText(this, "Your key is wrong", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }


    public void switchAlgho(View view) {
        RESET(null);
        String SwitchValue = Switch.getText().toString();
        switch (SwitchValue) {
            case "Advanced Encryption Standard":
                Textfield_Key.setInputType(InputType.TYPE_CLASS_NUMBER);
                Switch.setText("AES");
                break;

        }

    }




    public void RESET(View view) {
        Textfield_Text.setText("");
        Textfield_Key.setText("");
        Answer.setText("");
        if(view!=null)
        Toast.makeText(this, "All data has been deleted", Toast.LENGTH_SHORT).show();
    }



    public void copyToClipboard(View view) {
        if (Answer.length() == 0) {
          String copyText = String.valueOf(Answer.getText());
            if (Answer.length() == 0) {
                Toast.makeText(this, "There is no message to copy", Toast.LENGTH_SHORT).show();
                return;
            }

            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(copyText);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", copyText);
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(this,
                    "Your message has be copied", Toast.LENGTH_SHORT).show();
        } else {
            int sdk = android.os.Build.VERSION.SDK_INT;
            if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);

            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
                        .newPlainText("Your message :", Answer.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
            Toast.makeText(this, "Your message has be copied", Toast.LENGTH_SHORT).show();
        }
    }
}
