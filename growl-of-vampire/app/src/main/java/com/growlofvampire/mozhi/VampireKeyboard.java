package com.growlofvampire.mozhi;
import android.inputmethodservice.InputMethodService;import android.graphics.Color;import android.view.*;import android.view.inputmethod.InputConnection;import android.widget.*;
public final class VampireKeyboard extends InputMethodService {
 private StringBuilder word=new StringBuilder();private boolean caps=false;private LinearLayout root;
 private int d(int a){return (int)(a*getResources().getDisplayMetrics().density+.5f);}
 private Button key(String t, int weight){Button b=new Button(this);b.setText(t);b.setTextSize(18);b.setTextColor(Color.WHITE);b.setAllCaps(false);b.setPadding(0,0,0,0);b.setMinWidth(0);b.setMinimumWidth(0);b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xff472756));b.setOnClickListener(v->press(t));return b;}
 private void row(String chars){LinearLayout r=new LinearLayout(this);r.setOrientation(0);for(char c:chars.toCharArray()){Button b=key(""+c,1);LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0,d(52),1);lp.setMargins(d(2),d(3),d(2),d(3));r.addView(b,lp);}root.addView(r);}
 @Override public View onCreateInputView(){root=new LinearLayout(this);root.setOrientation(1);root.setPadding(d(4),d(6),d(4),d(10));root.setBackgroundColor(0xff261331);
 TextView label=new TextView(this);label.setText("GROWL OF VAMPIRE  •  Malayalam Mozhi");label.setTextSize(12);label.setTextColor(0xffead4f5);label.setGravity(Gravity.CENTER);root.addView(label);
 row("qwertyuiop");row("asdfghjkl");row("zxcvbnm");
 LinearLayout bottom=new LinearLayout(this);bottom.setOrientation(0);
 String[] keys={"⇧","⌫",",","SPACE",".","↵"};
 int[] w={1,1,1,4,1,1};
 for(int i=0;i<keys.length;i++){Button b=key(keys[i],w[i]);LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(0,d(56),w[i]);lp.setMargins(d(2),d(3),d(2),d(3));bottom.addView(b,lp);}root.addView(bottom);return root;}
 private void flush(){InputConnection ic=getCurrentInputConnection();if(ic!=null&&word.length()>0){String roman=word.toString();ic.deleteSurroundingText(roman.length(),0);ic.commitText(Mozhi.convert(roman),1);word.setLength(0);}}
 private void press(String t){InputConnection ic=getCurrentInputConnection();if(ic==null)return;
 switch(t){case "⇧":caps=!caps;return;case "⌫":if(word.length()>0)word.deleteCharAt(word.length()-1);ic.deleteSurroundingText(1,0);return;case "SPACE":flush();ic.commitText(" ",1);return;case "↵":flush();ic.commitText("\n",1);return;case ",":case ".":flush();ic.commitText(t,1);return;default:String letter=caps?t.toUpperCase(java.util.Locale.ROOT):t;caps=false;word.append(letter);ic.commitText(letter,1);}}
 @Override public void onStartInput(android.view.inputmethod.EditorInfo a,boolean restart){super.onStartInput(a,restart);word.setLength(0);}
}
