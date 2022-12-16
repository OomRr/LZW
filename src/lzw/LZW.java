package lzw;
import java.util.*;
public class LZW {
    public static List<Integer> compression(String input)
    {
        int dicSize=128;
        List<Integer>sentNumbers = new ArrayList<>();
        Map<String,Integer> dictionary=new HashMap<>();
        //insert the numbers ascii to the map
        for (int i=0;i<dicSize;i++)
        {
            dictionary.put(String.valueOf((char)i),i);
        }
        //string to search about it in the input
        String found="";
        //search each character in the input
        for (char c : input.toCharArray())
        {//add the next char to the string which is found
            String toAdd=found+c;
            if (dictionary.containsKey(toAdd)){
                //toAdd becomes found because it is found so go add the next char and search until become not found so go to else...
                found=toAdd;
            }
            else {
                //add the found key to array
                sentNumbers.add(dictionary.get(found));
                //update the dictionary
                dictionary.put(toAdd,dicSize++);
                //make the found start from the last char
                found=String.valueOf(c);
            }
        }
        //if the else did not implement (as reached the last char of the array then nothing will be added)
        if (!found.isEmpty()){
            sentNumbers.add(dictionary.get(found));
        }
        return sentNumbers;
    }
    public static String decompression(List<Integer> encodedText)
    {
        int dictSize = 128;
        Map<Integer, String> dictionary = new HashMap<>();
        for(int i = 0; i < dictSize; i++)
        {
            dictionary.put(i, String.valueOf((char)i));
        }
        String characters = String.valueOf((char) encodedText.remove(0).intValue());
        StringBuilder result = new StringBuilder(characters);
        for(int code : encodedText)
        {
            String entry = dictionary.containsKey(code) ? dictionary.get(code) : characters + characters.charAt(0);
            result.append(entry);
            dictionary.put(dictSize++, characters + entry.charAt(0));
            characters = entry;
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        System.out.println("For compression enter'1' or '2' for decompression");
        int operation=cin.nextInt();
        if (operation==1){
            System.out.println("Enter the Text Sequence to be compressed : ");
            String input = cin.next();
            List<Integer> sequenceNum= compression(input);
            System.out.println("Compressed sequence: \n" + sequenceNum);
            System.out.println("wanna do decompression too ?(Y/N)");
            char ans=cin.next().charAt(0);
            if (ans=='Y'||ans=='y')
                System.out.println("Back to original text: \n" + decompression(sequenceNum));
        }
        else {
            List<Integer> sequenceNum=new ArrayList<>();
            System.out.println("the numbers of integers");
            int siz=cin.nextInt();
            System.out.println("Enter the integers Sequence to be decompressed : ");
            for (int i = 0; i <siz ; i++) {
                sequenceNum.add(i,cin.nextInt());
            }
            System.out.println("The original text is: \n" + decompression(sequenceNum));
        }
}
}