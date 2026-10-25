package com.growlofvampire.mozhi;
import java.util.*;
public final class Mozhi {
 private static final LinkedHashMap<String,String[]> vs=new LinkedHashMap<>();
 private static final LinkedHashMap<String,String> cs=new LinkedHashMap<>();
 private static final Map<String,String> chill=new HashMap<>();
 static {
  v("aa","ആ","ാ");v("A","ആ","ാ");v("ii","ഈ","ീ");v("ee","ഈ","ീ");v("I","ഈ","ീ");v("uu","ഊ","ൂ");v("oo","ഊ","ൂ");v("U","ഊ","ൂ");v("ai","ഐ","ൈ");v("au","ഔ","ൗ");v("ou","ഔ","ൗ");v("E","ഏ","േ");v("O","ഓ","ോ");v("a","അ","");v("i","ഇ","ി");v("u","ഉ","ു");v("e","എ","െ");v("o","ഒ","ൊ");
  c("ksh","ക്ഷ");c("Ksh","ക്ഷ");c("nch","ഞ്ച");c("nT","ണ്ട");c("nt","ൻ്റ");c("kh","ഖ");c("gh","ഘ");c("ch","ച");c("Ch","ഛ");c("jh","ഝ");c("nj","ഞ");c("ng","ങ");c("th","ത");c("dh","ധ");c("ph","ഫ");c("bh","ഭ");c("sh","ഷ");c("zh","ഴ");c("Th","ഠ");c("Dh","ഢ");c("nh","ണ");c("lh","ള");c("rr","റ");
  c("k","ക");c("g","ഗ");c("c","ച");c("j","ജ");c("T","ട");c("D","ഡ");c("N","ണ");c("t","റ്റ");c("d","ദ");c("n","ന");c("p","പ");c("f","ഫ");c("b","ബ");c("m","മ");c("y","യ");c("r","ര");c("l","ല");c("v","വ");c("w","വ");c("S","ശ");c("z","ശ");c("s","സ");c("h","ഹ");c("L","ള");c("Z","ഴ");c("R","റ");c("K","ക്ക");c("P","പ്പ");c("M","മ്മ");
  chill.put("n","ൻ");chill.put("N","ൺ");chill.put("r","ർ");chill.put("l","ൽ");chill.put("L","ൾ");chill.put("m","ം");
 }
 private static void c(String s,String v){cs.put(s,v);}
 private static void v(String s,String a,String b){vs.put(s,new String[]{a,b});}
 private static String longest(String s,int i,Map<String,?> mp){String r=null;for(String k:mp.keySet())if(s.startsWith(k,i)&&(r==null||k.length()>r.length()))r=k;return r;}
 public static String convert(String s){
  if(s==null||s.isEmpty())return "";
  StringBuilder out=new StringBuilder();String consonant=null,roman=null;
  for(int i=0;i<s.length();){
   char ch=s.charAt(i);
   if(ch=='-'||ch=='_'){if(consonant!=null){out.append(chill.getOrDefault(roman,consonant+"്"));consonant=null;}i++;continue;}
   if(!(ch<128&&Character.isLetter(ch))){if(consonant!=null){out.append(chill.getOrDefault(roman,consonant+"്"));consonant=null;}out.append(ch);i++;continue;}
   String vowel=longest(s,i,vs),cons=longest(s,i,cs);
   if(vowel!=null&&(cons==null||vowel.length()>=cons.length())){
    String[] pair=vs.get(vowel);
    if(consonant!=null){out.append(consonant).append(pair[1]);consonant=null;}else out.append(pair[0]);
    i+=vowel.length();continue;
   }
   if(cons!=null){
    if(consonant!=null)out.append(consonant).append("്");
    consonant=cs.get(cons);roman=cons;i+=cons.length();continue;
   }
   if(consonant!=null){out.append(consonant).append("്");consonant=null;}out.append(ch);i++;
  }
  if(consonant!=null)out.append(chill.getOrDefault(roman,consonant+"്"));
  return out.toString();
 }
 private Mozhi(){}
}
