package mondaini.android.bagulhodoido.activities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mondaini.android.dojorio.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class License extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout layout = new LinearLayout(this);
		TextView license = new TextView(this);
		
		layout.addView(license, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f));
		
		license.setText(readTextFile());
		license.setMovementMethod(new ScrollingMovementMethod());
		
		setContentView(layout);
	}

	private String readTextFile(){
		InputStream inputStream = getResources().openRawResource(R.raw.license);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int i;
		try {
			i = inputStream.read();
			while (i != -1)
			{
				byteArrayOutputStream.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return byteArrayOutputStream.toString();
	}
}
