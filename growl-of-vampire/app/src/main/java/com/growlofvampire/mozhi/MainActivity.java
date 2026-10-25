package com.growlofvampire.mozhi;
import android.app.*;import android.content.*;import android.graphics.Color;import android.os.Bundle;import android.provider.Settings;import android.text.*;import android.view.*;import android.view.inputmethod.InputMethodManager;import android.widget.*;
public final class MainActivity extends Activity {
 private EditText input;private TextView output;
 private int dp(int x){return (int)(x*getResources().getDisplayMetrics().density+.5f);}
 private TextView text(String s,int size,int color){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(color);t.setPadding(0,dp(8),0,dp(8));return t;}
 private Button button(String s,java.util.function.Consumer<View> callback){Button b=new Button(this);b.setText(s);b.setOnClickListener(v->callback.accept(v));return b;}
 @Override public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(Color.rgb(22,12,31));getWindow().setNavigationBarColor(Color.rgb(22,12,31));
 ScrollView scroll=new ScrollView(this);scroll.setBackgroundColor(Color.rgb(22,12,31));LinearLayout root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(22),dp(28),dp(22),dp(32));scroll.addView(root);
 root.addView(text("GROWL OF VAMPIRE",28,Color.rgb(240,222,248)));root.addView(text("Malayalam • Mozhi-style typing • Offline",15,Color.rgb(194,168,205)));
 root.addView(text("Type Manglish below",17,Color.WHITE));input=new EditText(this);input.setTextColor(Color.WHITE);input.setHintTextColor(0xffcbb5d1);input.setHint("namaskaaram, malayaaLam, nandi");input.setMinLines(4);input.setGravity(Gravity.TOP);input.setSingleLine(false);input.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xff9f6db3));root.addView(input);
 root.addView(text("Malayalam output",17,Color.WHITE));output=text("",24,Color.rgb(243,224,253));output.setMinHeight(dp(100));output.setTextIsSelectable(true);root.addView(output);
 input.addTextChangedListener(new TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int count,int after){}public void onTextChanged(CharSequence s,int start,int before,int count){output.setText(Mozhi.convert(s.toString()));getPreferences(MODE_PRIVATE).edit().putString("draft",s.toString()).apply();}public void afterTextChanged(Editable e){}});
 root.addView(button("COPY MALAYALAM",v->{((android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Malayalam",output.getText()));Toast.makeText(this,"Copied",Toast.LENGTH_SHORT).show();}));
 root.addView(button("SHARE",v->{Intent i=new Intent(Intent.ACTION_SEND);i.setType("text/plain");i.putExtra(Intent.EXTRA_TEXT,output.getText().toString());startActivity(Intent.createChooser(i,"Share Malayalam"));}));
 root.addView(button("ENABLE MALAYALAM KEYBOARD",v->startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))));
 root.addView(button("SELECT KEYBOARD",v->{InputMethodManager imm=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);imm.showInputMethodPicker();}));
 root.addView(text("Type English letters to convert offline. The keyboard converts a word when you press space or punctuation. This is a Mozhi-inspired prototype, not the original Varamozhi engine.",13,0xffc9aecf));
 setContentView(scroll);input.setText(getPreferences(MODE_PRIVATE).getString("draft","")); }
}
