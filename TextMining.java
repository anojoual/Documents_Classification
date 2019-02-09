/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import static java.lang.Math.round;
import java.util.Collections;
import java.util.Enumeration;

import java.util.Hashtable;

import khoja.ArabicStemmerKhoja ;

public class TextMining {

    

    public static LinkedList<LinkedList> LireFile(String path , String separateur){
       LinkedList<LinkedList> list = new LinkedList();
    try {
      File f = new File(path);
      if (f.exists()) {
        FileReader fr = new FileReader(f);
        LineNumberReader in = new LineNumberReader(fr);
        String row;
                                
        while ( (row = in.readLine()) != null) {
                               
                                    LinkedList ling = new LinkedList();
                                    if(separateur.equals("") || separateur.equals(" ") || separateur.equals("  ") || separateur.equals("   ")){
                                        StringTokenizer st = new StringTokenizer(row);
                                        while (st.hasMoreTokens()) {
                                            ling.add(st.nextToken());
                                            

                                        }
                                    }else { 
                                        StringTokenizer st = new StringTokenizer(row,separateur);
                                        while (st.hasMoreTokens()) {
                                        ling.add(st.nextToken());
                                     
                                        
                                        }
                                    }
                                    
                                    
                                    list.add(ling);
                                    
        }
                                
                               
                                
                                
        
      }
                        else System.out.println("Le fichier choisis est introuvable..... ");
    }
    catch(Exception e) {
      System.out.println("Erreur : " + e.getMessage());
    }
               
        return list;
   }
   
    public LinkedList FilesDirectory (String path ) {
    
        LinkedList listFiles = new LinkedList();
            File folder = new File(path);
           File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
           // System.out.println("File " + listOfFiles[i].getName());
             
          } else if (listOfFiles[i].isDirectory()) {
            //System.out.println("Directory " + listOfFiles[i].getName());
          }
          
          listFiles.add(listOfFiles[i].getAbsolutePath());
        }
        
        return listFiles ;
    } 
    
    public LinkedList<String> TraitementFile(String path , String separateur, LinkedList<String> StopWords){
       LinkedList<String> list = new LinkedList();
       LinkedList<String> stop_word = new LinkedList();
    try {
      File f = new File(path);
      if (f.exists()) {
        FileReader fr = new FileReader(f);
        LineNumberReader in = new LineNumberReader(fr);
        String row;
                                
        while ( (row = in.readLine()) != null) {
                                    
                                    //System.out.println("row : "+row);
                                    
                                    StringTokenizer st = new StringTokenizer(row, separateur);
                                    while (st.hasMoreTokens()) {
                                        String str = st.nextToken();
                                        if(StopWords.contains(str.toLowerCase())){
                                            stop_word.add(str);
                                        }
                                        else {
                                            list.add(str);
                                        }

                                    }
                                       
                                    
                                    }
                                    
                                    
        }
                        
                        else System.out.println("Le fichier choisis est introuvable..... ");
    }
    catch(Exception e) {
      System.out.println("Erreur : " + e.getMessage());
    }
                //System.out.println("NEW WORDS ARE : "+list);
                
                //System.out.println("STOPED WORDS ARE : "+ stop_word);
                
                
        return list;
   }
    
    
    public LinkedList wordsNoSep (String text  ) {
    
          LinkedList listmots = new LinkedList();
          StringTokenizer token = new StringTokenizer(text ," .,/;:'*-?!&^{}[]|\"");
          while(token.hasMoreElements()){
            listmots.add(token.nextToken()) ;
                                        }
    
                                        return listmots ;
    }
    
    
 public static boolean ExistListe(LinkedList list , String nom) {
        
for(int i = 0 ; i < list.size() ; i++) {
          
if(list.get(i).equals(nom)) 
    return true ;
    }
  return false  ;
}

      public static int Occurence(LinkedList list , String nom) {
        
        int counter = 0 ;
        for(int i=0 ; i < list.size() ; i++ ) {
          if(list.get(i).equals(nom.toLowerCase())) {
              counter++ ;
          } else {
          }
          
        }
        return counter ;
    }

      public LinkedList AllWords (LinkedList<LinkedList> documents ) {
      
           LinkedList allwords = new LinkedList();
          LinkedList ham = new LinkedList();
          
          
          for (int i = 0 ; i < documents.size() ; i++){
          
              for (int j = 0 ; j < documents.get(i).size() ; j++) {

                      ham.add(documents.get(i).get(j));
                  }

              }
          allwords.add( ham.get(0));
         
          int drp = 1 ; 
          
         
              for (int t = 1 ; t <ham.size();t++){
                   drp=1;
                  if (!termedocs(allwords, (String) ham.get(t))){
                  
                      drp = 0 ;
                  }
                  
                  if (drp == 0) {
                     // System.out.println("=====");
                  allwords.add(ham.get(t));
                  }
              
          }
          
          return allwords;
          
      }
    public double TF (LinkedList document , String terme ) {
    double frr = 0.0 ;
          for (int i = 1 ; i < document.size() ; i++) {
            int cnt = 0 ;
            
            cnt = TextMining.Occurence(document, terme);
            frr = 0.0 + (double)cnt/document.size() ;
          }
          return frr ;
    }
    
     
    public double  occuDoc (LinkedList<LinkedList> documents ,  String terme ) {
    double idf = 0.0 ;
    
    int cnt = 0 ;
    
          for (int i = 0 ; i < documents.size() ; i++) {
            
        
            if(documents.get(i).contains(terme)) {

            cnt ++ ;
                   }
            
          }
          idf = 0.0 + Math.log((double)documents.size()/cnt) ;
          
          
       return idf ; 
    }
    
    
public boolean termedocs ( LinkedList documents , String terme ) {

    for(int i = 0 ; i < documents.size() ; i++) {
    
        if (documents.get(i).equals(terme)) {
        return true ;
        }
    }
    return false ;
}

public LinkedList hah (LinkedList<LinkedList> documents ) {

    LinkedList<LinkedList> h = new LinkedList<LinkedList> () ;
    
    int drp = 1 ;
    for (int i = 0 ; i < documents.size() ; i++ ){
    
        LinkedList l1 = new LinkedList();
        l1.add(documents.get(i).get(0));
        for (int j = 1 ; j < documents.get(i).size() ; j++) {
        drp=1;
            if (!termedocs(l1, (String) documents.get(i).get(j))) {
                
              drp = 0 ;  
            }
        
            if (drp==0) {
                l1.add(documents.get(i).get(j));
                
                
     }
        }
        h.add(l1);
        
    }
return h ;
}
    
    
    public  LinkedList<LinkedList<LinkedList>> matrice (LinkedList<LinkedList> documents ) {
        
        LinkedList<LinkedList<LinkedList>> global = new LinkedList<LinkedList<LinkedList>>();
        LinkedList allwords = AllWords(documents);
        
               

        
       double  tf = 0.0 ;
       double idf = 0.0 ;
       double w = 0.0 ;
        
       for (int f = 0 ; f < allwords.size() ; f++){
           
           LinkedList<LinkedList> listdocfre = new LinkedList<LinkedList>();
        for (int i = 0 ; i < documents.size() ; i++) {
            LinkedList hash = new LinkedList();
             Hashtable ht = new Hashtable();
            LinkedList d = new LinkedList ();
            int drp=0;
 
              if (termedocs(documents.get(i), (String) allwords.get(f))) {
                 drp = 1 ;

              }
                 if (drp == 1) {
              tf = TF(documents.get(i), (String) allwords.get(f));
              
              idf = occuDoc(documents, (String) allwords.get(f));
              w = tf*idf ;
              
              d.add(allwords.get(f));
              d.add(w);
              
              ht.put(i,documents.get(i));
              hash.add(ht);
              
              listdocfre.add(d);

              listdocfre.add(hash); 
                  
              }
          }
         
            
            global.add(listdocfre);
        }
        
       
        return global ;
    
    }
    
    public int OccuWord (LinkedList wordsOfListes , String word) {
     int drp = 0 ;
               int cnt = 0 ;
         for (int h = 0  ; h<wordsOfListes.size() ; h++) {

                 if (wordsOfListes.get(h).equals(word)) {
                 
                     drp = 1 ;
                 }
                 if (drp == 1) {
                 
                     cnt ++;
                 }
             
         }
         return cnt ;
    }
    
    
    public LinkedList<LinkedList> wordsOfDirec (LinkedList filepaths , String sep , LinkedList stopwords  ) {
    
        LinkedList words = new LinkedList();
       
          LinkedList<LinkedList> h =  new LinkedList<LinkedList>();
          LinkedList<LinkedList> ha1 = new LinkedList<>();
         for (int o = 0 ; o < filepaths.size() ; o++) {

               LinkedList<LinkedList> ha = new LinkedList<LinkedList> ();
                            LinkedList t = TraitementFile((String)filepaths.get(o),sep,stopwords);


                            ha.add(t);
                            for (int i = 0 ; i < ha.size() ; i++) {
                                
                            LinkedList t1 = new LinkedList();
                                for (int j = 0 ; j  <  ha.get(i).size() ; j++) {
                                    
                                ArabicStemmerKhoja f = new ArabicStemmerKhoja();

                               String resut = f.stem((String) ha.get(i).get(j));

                                            t1.add(resut);

                                }
                               ha1.add(t1);
                            } 

                           h = hah(ha1);
                        
         }
      return h ;
  
    }
    
    
    public LinkedList wordsWithRep (LinkedList files , String sep , LinkedList stopwords) {
    
        LinkedList<LinkedList> wt = new LinkedList<LinkedList>();
       
        for (int d = 0 ; d < files.size() ; d++) {
            
           LinkedList w = TraitementFile((String) files.get(d), sep, stopwords);
             
           wt.add(w);
        }

        LinkedList all = new LinkedList();
        for (int s = 0 ; s < wt.size() ; s++) {
        
            for (int v = 0 ; v < wt.get(s).size();v++ ){
             ArabicStemmerKhoja f = new ArabicStemmerKhoja();

                               String result = f.stem((String) wt.get(s).get(v));

                all.add(result);
            }
        }
        
        return all;
    }
    
    
    
    public LinkedList matriceClasse (LinkedList paths  , String sep , LinkedList stopwords ) {
    
        LinkedList matriceClass = new LinkedList () ;
        for(int r = 0 ; r <paths.size() ; r++){
            Hashtable hd = new Hashtable();
            
        LinkedList files = FilesDirectory((String) paths.get(r));
        LinkedList<LinkedList> words = wordsOfDirec(files, sep, stopwords);
        
        LinkedList wordsRep = wordsWithRep(files, sep, stopwords);
        
        int cnt = 0 ;
        int drp = 0 ;
       
         LinkedList ttt = new LinkedList();  
            
        for(int m = 0 ; m < words.size() ; m++) {
            
         Hashtable ht = new Hashtable();
         LinkedList<LinkedList> kk = new LinkedList<>();
           
            for(int n = 0 ; n < words.get(m).size() ; n++){
             LinkedList occ = new LinkedList(); 
            cnt = Occurence(wordsRep,(String) words.get(m).get(n));

           occ.add(words.get(m).get(n));
           occ.add(cnt+1);
          kk.add(occ);
          
                    }
            ht.put(files.get(words.indexOf(words.get(m))),kk);
            ttt.add(ht);
          
        }
          hd.put(paths.get(r), ttt);
             
           matriceClass.add(hd);
        }
        

        return matriceClass ;
    }
    
    public LinkedList<LinkedList> listOflist (LinkedList<LinkedList> li) {
    
        LinkedList ma =  new LinkedList();
        LinkedList ma1 = new LinkedList();
        LinkedList<LinkedList> ma2 = new LinkedList<LinkedList> ();
        LinkedList<LinkedList> lis = new LinkedList<LinkedList> ();
        int drp = 0 ;
        for (int i = 0 ; i < li.size() ; i++){
        
            ma.add(li.get(i).get(0));
        }
       ma2.add(ma);
        ma1 = AllWords(ma2);
        //System.out.println("ma = "+ma2);
        //System.out.println("ma1 ="+ma1);
      
        for (int i = 0 ; i < ma1.size() ; i++){
        
            LinkedList na = new LinkedList();

          //  System.out.println("li ="+li);
                na.add(ma1.get(i));
                na.add(li.get(ma1.indexOf(ma1.get(i))).get(1));
                lis.add(na);
            
        }
                
          //  System.out.println(" lis ="+lis);
    
        
        
        return lis ;
    }
    
    
    
    public LinkedList<LinkedList<LinkedList>> probWordClass (LinkedList paths  , String sep , LinkedList stopwords ) {
   
        LinkedList<LinkedList<LinkedList>> allprob = new LinkedList<LinkedList<LinkedList>>();
        
        double prob = 0.0 ;
         LinkedList<LinkedList<LinkedList>>  aaa1 = new LinkedList<LinkedList<LinkedList>> ();
        LinkedList mat = matriceClasse(paths, sep, stopwords);
        //System.out.println(" mat ="+mat);
        LinkedList somme = new LinkedList();
        for (int i = 0 ; i < mat.size() ; i++) {
            
            int s = 0 ;
         LinkedList<LinkedList>  aaa = new LinkedList<LinkedList> ();
            Hashtable h = (Hashtable) mat.get(i);
           // System.out.println(" h ="+mat.get(i));
            LinkedList<LinkedList> li = new LinkedList<LinkedList>(h.values());
           // System.out.println(" li ="+li);
           for(int t = 0 ; t<li.size() ; t++){
               
            for (int f = 0 ; f < li.get(t).size() ; f++){
                //System.out.println(" liiiiii ="+li.get(t).get(f));
                Hashtable  h1 =  (Hashtable) li.get(t).get(f);
                LinkedList<LinkedList<LinkedList>> li1 = new LinkedList<LinkedList<LinkedList>>(h1.values());
                
               // System.out.println(" li1 ="+li1);
                
                 for(int l = 0 ; l<li1.size() ; l++){
               
            for (int n = 0 ; n < li1.get(l).size() ; n++){
                
             aaa.add((LinkedList) li1.get(l).get(n));
             
            }
           }
             
            
            for (int p = 0 ; p < li1.size() ; p++){
                for (int r = 0 ; r < li1.get(p).size();r++){
                
              // System.out.println("cast ="+li1.get(p).get(r));
                   s = s + (Integer)li1.get(p).get(r).get(1);
                   

            }
            
            }   
                 
            }
            
           }
           somme.add(s);
            
               aaa1.add(aaa);
            
        }
        //System.out.println(" s ="+somme);
       // System.out.println("aaa ="+aaa1);
        
       for (int a = 0 ; a < aaa1.size() ; a++){
          LinkedList<LinkedList> listprob = new LinkedList();
        LinkedList<LinkedList> hahh = listOflist(aaa1.get(a));
        
        //System.out.println("hahhh  ="+hahh);

            for (int y = 0 ; y < hahh.size() ; y++){

                LinkedList pr = new LinkedList();
                  // System.out.println("somme ="+hahh.get(y));
                   prob = 0.0 + (double) (Integer)hahh.get(y).get(1)/(Integer)somme.get(a) ;
                   pr.add(hahh.get(y).get(0));
                   pr.add(prob);
                  listprob.add(pr);
                  
                }
           allprob.add(listprob);
       }
       
        
        return allprob ;
    }
    
    
    
    public LinkedList<LinkedList> probClass (LinkedList paths){
    
        LinkedList<LinkedList> prb = new LinkedList();
        int all = 0 ;
        double pb = 0.0 ;
        for (int i = 0 ; i < paths.size() ; i++){
            
         LinkedList files = FilesDirectory((String) paths.get(i));
            
         all = all + files.size();
        }
        
        for (int j = 0 ; j < paths.size() ; j++) {
            LinkedList g = new LinkedList();
        
            LinkedList files = FilesDirectory((String) paths.get(j));
            
            pb =  0.0 + (double) files.size()/all ;
            
            g.add(paths.get(j));
            g.add(pb);
            prb.add(g);
        }
        
        return prb ;
    }
    
    
    public LinkedList probaDocClass (LinkedList paths  , String sep , LinkedList stopwords) {
    
        double probAll = 0.0 ;
        LinkedList listprob = new LinkedList();
        for (int i =0 ; i < paths.size() ; i++){
            
//         LinkedList<LinkedList> h = probWordClass((String) paths.get(i), sep, stopwords);
         
         Hashtable classDoc = new Hashtable();
      //   classDoc.put(paths.get(i), h);

        }
        
        
        return listprob;
    }
    
    
    
    public LinkedList MatriceGlobal (LinkedList paths  , String sep , LinkedList stopwords) {
    
      
        LinkedList matr = new LinkedList();
        LinkedList<LinkedList> woor = new LinkedList<LinkedList> ();
        int cnt = 0 ;
         
        LinkedList yu = new LinkedList();
        LinkedList<LinkedList> yuu = new LinkedList<>();
        LinkedList yu1 = new LinkedList();
        
        for(int r = 0 ; r <paths.size() ; r++){
            
            
        LinkedList files = FilesDirectory((String) paths.get(r));
        LinkedList<LinkedList> words = wordsOfDirec(files, sep, stopwords);
        
        LinkedList wordsRep = wordsWithRep(files, sep, stopwords);
            // System.out.println(" words ="+words);
            // System.out.println(" wordRep  ="+wordsRep);
             woor.add(wordsRep);
             
             
        }
           //  System.out.println(" woor ="+woor);
             
             for ( int h = 0 ; h< woor.size() ; h++){
             
                 for (int y = 0 ; y<woor.get(h).size() ; y++){
                 
                     yu.add(woor.get(h).get(y));
                 }
             }
         
             yuu.add(yu);
             yu1 = AllWords(yuu);
             // System.out.println(" yu1 ="+yu);
              
             
             
 Hashtable hd = new Hashtable();
            
             for (int u = 0 ; u < woor.size() ; u++){
                  // System.out.println(" u ="+woor.get(u));
LinkedList<LinkedList> prr = new LinkedList<>();
             for (int e = 0 ; e < yu1.size() ; e++){

                LinkedList pr = new LinkedList();
                     cnt = Occurence(woor.get(u), (String) yu1.get(e));
                     pr.add(yu1.get(e));
                     pr.add(cnt +1);
                     prr.add(pr);
                     
                 }
                 //System.out.println("prr ="+prr + "  u= "+u);
        hd.put(paths.get(u),prr);
             }
             
           
    matr.add(hd);
              
         
        return matr ;
    }
    
    
    
    public LinkedList<LinkedList> probWordClass22 (LinkedList paths  , String sep , LinkedList stopwords ) {
   
        LinkedList allprob = new LinkedList();
        
        double prob = 0.0 ;
         LinkedList<LinkedList<LinkedList>>  aaa1 = new LinkedList<LinkedList<LinkedList>> ();
        LinkedList mat = MatriceGlobal(paths, sep, stopwords);
       // System.out.println(" mat ="+mat);
        LinkedList somme = new LinkedList();
        //int s = 0 ;
        for (int i = 0 ; i < mat.size() ; i++) {
            
         LinkedList<LinkedList>  aaa = new LinkedList<LinkedList> ();
            Hashtable h = (Hashtable) mat.get(i);
           // System.out.println(" h ="+mat.get(i));
            LinkedList<LinkedList<LinkedList>> li = new LinkedList<LinkedList<LinkedList>>(h.values());
          // System.out.println(" li ="+li);
           for(int t = 0 ; t<li.size() ; t++){

                 for(int l = 0 ; l<li.get(t).size() ; l++){
 
             aaa.add( li.get(t).get(l));
             
            }
           }
             
          //  System.out.println("aaa ="+aaa);
            for (int p = 0 ; p < li.size() ; p++){
                int s = 0 ;
                
                for(int r = 0 ; r < li.get(p).size() ; r++){
              // System.out.println("cast ="+li1.get(p).get(r));
                   s = s + (Integer)li.get(p).get(r).get(1);
            }
                somme.add(s);
            }             
 
             for (int y = 0 ; y < li.size() ; y++){
                 Hashtable  ha = new Hashtable();
                 LinkedList<LinkedList> listprob = new LinkedList<>();
                 for (int r = 0 ; r < li.get(y).size() ; r++){
 
                LinkedList pr = new LinkedList();
                  // System.out.println("somme ="+hahh.get(y));
                   prob = 0.0 + (double) (Integer)li.get(y).get(r).get(1)/(Integer)somme.get(y) ;
                   pr.add(li.get(y).get(r).get(0));
                   pr.add(prob);
                  listprob.add(pr);
                  ha.put(paths.get(y), listprob);
                  
                }
             
           allprob.add(ha);
             }
            }
            
       
        
        return allprob ;
    }
    
    
    
     public LinkedList MatriceGlobalTest (String filepath , LinkedList paths  , String sep , LinkedList stopwords) {
    
      
        LinkedList matr = new LinkedList();
        LinkedList<LinkedList> woor = new LinkedList<LinkedList> ();
        int cnt = 0 ;
         
        LinkedList yu = new LinkedList();
        LinkedList<LinkedList> yuu = new LinkedList<>();
        LinkedList yu1 = new LinkedList();
        
        LinkedList files = new LinkedList();
        
        for(int r = 0 ; r <paths.size() ; r++){
            
            
         files = FilesDirectory((String) paths.get(r));
        files.add(filepath);
          
        LinkedList<LinkedList> words = wordsOfDirec(files, sep, stopwords);
            //System.out.println(" words ="+words);
        LinkedList wordsRep = wordsWithRep(files, sep, stopwords);
            // System.out.println(" words ="+words);
            // System.out.println(" wordRep  ="+wordsRep);
             woor.add(wordsRep);
             
             
        }
       // System.out.println("files ="+files);
           //  System.out.println(" woor ="+woor);
             
             for ( int h = 0 ; h< woor.size() ; h++){
             
                 for (int y = 0 ; y<woor.get(h).size() ; y++){
                 
                     yu.add(woor.get(h).get(y));
                 }
             }
         
             yuu.add(yu);
             yu1 = AllWords(yuu);
            //  System.out.println(" yu1 ="+yu1);
              
             
             
 Hashtable hd = new Hashtable();
            
             for (int u = 0 ; u < woor.size() ; u++){
                   //System.out.println(" u ="+woor.get(u));
LinkedList<LinkedList> prr = new LinkedList<>();
             for (int e = 0 ; e < yu1.size() ; e++){

                LinkedList pr = new LinkedList();
                     cnt = Occurence(woor.get(u),(String) yu1.get(e));
                     pr.add(yu1.get(e));
                     pr.add(cnt +1);
                     prr.add(pr);
                     
                 }
                 //System.out.println("prr ="+prr + "  u= "+u);
        hd.put(paths.get(u),prr);
             }
             
           
    matr.add(hd);
              
         
        return matr ;
    }
    
    
    
    public LinkedList probWordClassTest ( String filepath , LinkedList paths  , String sep , LinkedList stopwords ) {
   
        LinkedList allprob = new LinkedList();
        
        double prob = 0.0 ;
         LinkedList<LinkedList<LinkedList>>  aaa1 = new LinkedList<LinkedList<LinkedList>> ();
        LinkedList mat = MatriceGlobalTest(filepath , paths, sep, stopwords);
       // System.out.println(" mat ="+mat);
        LinkedList somme = new LinkedList();
        //int s = 0 ;
        for (int i = 0 ; i < mat.size() ; i++) {
            
         LinkedList<LinkedList>  aaa = new LinkedList<LinkedList> ();
            Hashtable h = (Hashtable) mat.get(i);
           // System.out.println(" h ="+mat.get(i));
            LinkedList<LinkedList<LinkedList>> li = new LinkedList<LinkedList<LinkedList>>(h.values());
          // System.out.println(" li ="+li);
           for(int t = 0 ; t<li.size() ; t++){

                 for(int l = 0 ; l<li.get(t).size() ; l++){
 
             aaa.add( li.get(t).get(l));
             
            }
           }
             
            //System.out.println("aaa ="+aaa);
            for (int p = 0 ; p < li.size() ; p++){
                int s = 0 ;
                
                for(int r = 0 ; r < li.get(p).size() ; r++){
              // System.out.println("cast ="+li1.get(p).get(r));
                   s = s + (Integer)li.get(p).get(r).get(1);
            }
                somme.add(s);
            }             
 
             for (int y = 0 ; y < li.size() ; y++){
                 Hashtable  ha = new Hashtable();
                 LinkedList<LinkedList> listprob = new LinkedList<>();
                 for (int r = 0 ; r < li.get(y).size() ; r++){
 
                LinkedList pr = new LinkedList();
                  // System.out.println("somme ="+hahh.get(y));
                   prob = 0.0 + (double) (Integer)li.get(y).get(r).get(1)/(Integer)somme.get(y) ;
                   pr.add(li.get(y).get(r).get(0));
                   pr.add(prob);
                  listprob.add(pr);
                  ha.put(paths.get(y), listprob);
                  
                }
             
           allprob.add(ha);
             }
            }
            
       
        
        return allprob ;
    }
    
     public LinkedList MatriceFileTest (String filetest , String sep , LinkedList stopwords) {
    
      
        LinkedList matr = new LinkedList();
        LinkedList<LinkedList> woor = new LinkedList<LinkedList> ();
        int cnt = 0 ;
         
        LinkedList yu = new LinkedList();
        LinkedList<LinkedList> yuu = new LinkedList<>();
        LinkedList yu1 = new LinkedList();
        
      
            
            
        LinkedList files = new LinkedList();
        files.add(filetest);
        LinkedList<LinkedList> words = wordsOfDirec(files, sep, stopwords);
        
        LinkedList wordsRep = wordsWithRep(files, sep, stopwords);
            // System.out.println(" words ="+words);
            // System.out.println(" wordRep  ="+wordsRep);
             woor.add(wordsRep);
             
             
        
           //  System.out.println(" woor ="+woor);
             
             for ( int h = 0 ; h< woor.size() ; h++){
             
                 for (int y = 0 ; y<woor.get(h).size() ; y++){
                 
                     yu.add(woor.get(h).get(y));
                 }
             }
         
             yuu.add(yu);
             yu1 = AllWords(yuu);
             // System.out.println(" yu1 ="+yu);
              
             
             
 Hashtable hd = new Hashtable();
            
             for (int u = 0 ; u < woor.size() ; u++){
                  // System.out.println(" u ="+woor.get(u));
LinkedList<LinkedList> prr = new LinkedList<>();
             for (int e = 0 ; e < yu1.size() ; e++){

                LinkedList pr = new LinkedList();
                     cnt = Occurence(woor.get(u), (String) yu1.get(e));
                     pr.add(yu1.get(e));
                     pr.add(cnt +1);
                     prr.add(pr);
                     
                 }
                 //System.out.println("prr ="+prr + "  u= "+u);
        hd.put(filetest,prr);
             }
             
           
    matr.add(hd);
              
         
        return matr ;
    }

    public LinkedList<LinkedList> Classfication (String fileTest , LinkedList probTest , String sep , LinkedList stopwords) {
    
        String Class = "";
        int drp = 0 ;
        LinkedList listprob = new LinkedList();
        LinkedList fil = new LinkedList();
        fil.add(fileTest);
        LinkedList<LinkedList> wordFileTest = wordsOfDirec(fil,sep,stopwords);
        LinkedList wordFileTest22 = new LinkedList();
        for (int t = 0 ; t < wordFileTest.size() ; t++){
        
            for (int p = 0; p < wordFileTest.get(t).size() ; p++){
            
                wordFileTest22.add(wordFileTest.get(t).get(p));
            }
        }
        
      //  LinkedList<LinkedList> w = new LinkedList<>();
      //  w.add(wordFileTest);
        //LinkedList wordFileTest11 = AllWords(w);
        
        LinkedList<LinkedList> listporbb = new LinkedList<>();
        for (int i = 0 ; i < probTest.size() ;i++){
             LinkedList pro = new LinkedList();
            
        Hashtable h = (Hashtable) probTest.get(i);
        LinkedList<LinkedList<LinkedList>> li = new LinkedList<LinkedList<LinkedList>>(h.values());
        LinkedList<LinkedList> li1 = new LinkedList<>();
        
        for (int u = 0 ; u < li.size() ; u++){
        
            for (int n = 0 ; n <li.get(u).size() ; n++){
            
                li1.add(li.get(u).get(n));
            }
        }
       //  System.out.println(" li Class ="+li1);
        //  System.out.println("wordFile  ="+wordFileTest22);
        for (int j = 0 ; j < li1.size() ; j++){
 drp=0;
                    if (wordFileTest22.contains(li1.get(j).get(0))){
                       // System.out.println("*************");
                        drp = 1 ;
                    }
                    
                    if (drp == 1) {
                      //  System.out.println(li1.get(j).get(0));
                        pro.add(li1.get(j).get(1));
                    }
                
            
            
            
        }
        listporbb.add(pro);
        }     
         //System.out.println(" pro  ="+listporbb);       
        return listporbb ;
    }
    
    
    public String ClassTestFinal (LinkedList<LinkedList>  listprobClassTest , LinkedList<LinkedList> classProb) {
    
        String classTest = "";
        double prd1 = 0.0 ;
        
        for (int i = 0 ; i < classProb.size() ; i++){
        
            double prd = 1.0;
            for (int j = 0 ; j < listprobClassTest.get(i).size() ; j++){
            
                prd = prd*(double)listprobClassTest.get(i).get(j)*(double)classProb.get(i).get(1); 
                //System.out.println(" ppppprddd = "+(double)classProb.get(1).get(1));
                
            }
            
            if (prd > prd1){
              //  System.out.println(" prd  ="+prd);
                prd1 = prd ;
                classTest = (String) classProb.get(i).get(0);
            }
            
        }
        
                
                return classTest ;
    }
    
    
    
    public static void main(String[] args) {

        TextMining g = new TextMining();

        
        // Liste des separateurs , vous pouvez ajouter des autres separateurs .
         String sep = "/*,.\"?! -:;" ;
         
         
         
        // liste des dossiers (Classes) contenant les documents 
         
        String pathDirect = "C:/Users/JOUAL.A/Desktop/MasterBigDATA/S3/TextMining/test";
        String pathdir = "C:/Users/JOUAL.A/Desktop/MasterBigDATA/S3/TextMining/test2";
        
        LinkedList listDir = new LinkedList();
        listDir.add(pathDirect);
        listDir.add(pathdir);
        
        
        // le chemin du document à classifier 
        String fileTest = "C:\\Users\\JOUAL.A\\Desktop\\MasterBigDATA\\S3\\TextMining\\texte.txt";
        

       
        LinkedList<LinkedList<LinkedList>> mat = new LinkedList<LinkedList<LinkedList>> () ;
        LinkedList sw = new LinkedList();
            
          
        // Liste des stopwords , indiquez le chemin 
        LinkedList stopwords = g.TraitementFile("C:/Users/JOUAL.A/Desktop/MasterBigDATA/S3/TextMining/stopwords.txt",sep,sw);
        

        LinkedList<LinkedList> ha = new LinkedList<LinkedList> ();
        
        
       LinkedList l = g.FilesDirectory(pathDirect) ;
       for (int q = 0 ; q < l.size() ; q++) {
       // System.out.println("files  =" +l.get(q));
       }
       //System.out.println("matriiice = " +mat);
        
       LinkedList<LinkedList> h = g.wordsOfDirec(l, sep, stopwords);

        
         LinkedList y = g.wordsWithRep(l, sep, stopwords);
         
         mat = g.matrice(h);
    
        
        
    // fonction qui retourne un map du map , premier map du classe sur les documents qui contient , 2eme map du document sur les mots qui contient + l'occurence
        LinkedList ta = g.matriceClasse(listDir, sep, stopwords);
        
       // System.out.println("taa ="+ta);
        
        
        
        
        // Liste des probabilites des mots dans chaque document 
        LinkedList<LinkedList<LinkedList>> hya = g.probWordClass(listDir, sep, stopwords);
        //System.out.println(" probabilite  ="+hya);
        
        
        
        // probabilite de chaque classe , vous pouvez faire l'afichage ci-dessous :
       LinkedList<LinkedList> ag = g.probClass(listDir);
       
      //  System.out.println("classs proba ="+ag);
        

       
       
       // matrice contenant l'occurence de chaque mot dans chque classe , sous forme du map du classe sur tous les mots de cette classe , l'affichage ci-dessous :
       LinkedList fre = g.MatriceGlobal(listDir, sep, stopwords);
      
       
       // System.out.println(" matriceGlobal ="+fre);
       
        
       
       // liste des probabilites des mots dans chaque classe , l'affichage ci-dessous :
        LinkedList<LinkedList>  prob22 = g.probWordClass22(listDir, sep, stopwords);
        
      //  System.out.println(" prob22  ="+prob22);
        
        
        
         // liste des probabilites des mots dans chaque classe , en ajoutant le nouveau document a classifier , l'affichage ci-dessous :
        LinkedList  probTest = g.probWordClassTest(fileTest, listDir, sep, stopwords);
        
       // System.out.println("probTest  ="+probTest);
        
        
        
        // liste du probabilites de chaque mot du nouveau document a classifier , dans chaque classe :
        LinkedList<LinkedList> clatest = g.Classfication(fileTest, probTest, sep, stopwords);
        
        
        
        // Resultat  du classification , on affiche a quelle classe (dossier) appartient le nouveau document :
        String ClassTestFinal = g.ClassTestFinal(clatest, ag);
        System.out.println(" le nouveau document appartient au dossier  (classe)   =  "+ClassTestFinal);
        
        
       /*
        for (int i = 0 ; i <fre.size() ; i++) {
        
            frequence f = (frequence) fre.get(i);
           // System.out.println(" mots  = " + f.getNom() + " frequence   = " + f.getFre());
            
            
        }
        */
        //System.out.println(mots); 
    }
    
}
