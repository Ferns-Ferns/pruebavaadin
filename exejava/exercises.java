import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class exercises {

    public static void main(String[] args) {

        // exercises.superTree(10);
        // exercises.sumLastDigits(9876);cl
        // exercises.anagrams("amor", "roma");
        // exercises.streamsDemo(Arrays.asList("Andrea", "Juana"));
        // exercises.fizzBuzz();
        // exercises.palindrome(1233231);
        // exercises.numberToString(16275542);
        // exercises.fibonacci(10);
        // exercises.twoSum(new int[]{2, 7, 11, 15}, 9);
        // exercises.isPalindrome(121);
        // exercises.romanToInt("MCMXXXCIV");
        // exercises.longestCommonPrefix(new String[] {"flower","flow","floght"});
        // exercises.isValidSign("()[]");
        // exercises.isSubSecuence(Arrays.asList(1,2,3,4), Arrays.asList(1,3,5));
        // exercises.removeDuplicates(new int[]{1,1,2});
        exercises.removeElement(new int[]{3,2,2,3}, 3);
    }

    public static void superTree(int filas) {
        for (int i = 1; i <= filas; i++){
            for (int j = 1; j <= i; j++){
                System.out.print(j);
            }
            System.out.println();   
        }
    }

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        return set.size();
    }

    public static int removeElement(int[] nums, int val) {
        if(nums.length  == 0){
            return 0;
        }

        int count = 0;
        for(int i = 0; i < nums.length ; i++){
            if (nums[i] == val){
                count++;
            }
        }
        System.out.println(count);  
        return count;
    }

    public static void sumLastDigits(int number){

        while (number >= 10) {
            int result = 0;
            while (number > 0) {
                int lastDigit = number % 10;
                result += lastDigit;
                number = number / 10;
            }
            number = result;
        }
        
        System.out.println(number);
    }

    public static void anagrams(String word1, String word2){
        if (word1.length() != word2.length()){
            System.out.println("No son anagramas");
        } else {
            char[] word1Array = word1.toCharArray();
            char[] word2Array = word2.toCharArray();

            Arrays.sort(word1Array);
            Arrays.sort(word2Array);
            
           boolean result = Arrays.equals(word1Array, word2Array);
           System.out.println(Boolean.toString(result));   
            
        }
    }

    public static void streamsDemo(List<String> list){
        List<String> result = list.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
        System.out.println(result);

        List<String> result2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(result2);
    }

    public static void fizzBuzz(){
        for(int i = 1; i<=50; i++){
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            }else if (i % 5 == 0) {
                System.out.println("Buzz");
            }else if (i % 3 == 0) {
                System.out.println("Fizz");
            }else {
                System.out.println(i);
            }
        }
    }

    public static void palindrome(int number){
        String numberString = Integer.toString(number);
        String reverseNumber = new StringBuilder(numberString).reverse().toString();

        if (numberString.equals(reverseNumber)){
            System.out.println("Es palindromo");
        }else {
            System.out.println("No es palindromo");
        }
    }

    public static void fibonacci(int n){
        int a = 0;
        int b = 1;
        int c = 0;
        System.out.print(a + " " + b + " ");
        for(int i = 0; i < n; i++){
            c = a + b;
            a = b;
            b = c;
            System.out.print(c + " ");
        }

        long[] fibonacci = new long[n];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for(int i = 2; i < n;i++){
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }
        System.out.println(Arrays.toString(fibonacci));
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {}; 
    }

    public static boolean isPalindrome(int x) {
        String numbText = Integer.toString(x);
        String reverseNumb = new StringBuilder(numbText).reverse().toString();
        if (numbText.equals(reverseNumb)){
            return true;
        }
        return false;
    }

    public static int romanToInt(String s) {
        int result = 0;
        Map<Character, Integer> romanValues = new HashMap<Character, Integer>();

        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        s = s.replace("IV", "IIII");
        s = s.replace("IX", "VIIII");
        s = s.replace("XL", "XXXX");
        s = s.replace("XC", "LXXXX");
        s = s.replace("CD", "CCCC");
        s = s.replace("CM", "DCCCC");

        for(int i = 0; i<s.length(); i++){
            result += romanValues.get(s.charAt(i));
        }
        System.out.println(result);
        return result;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String base = strs[0]; 
        for(int i = 0; i<base.length(); i++){
            for(String word : strs){
                if(i == word.length() || word.charAt(i) != base.charAt(i)){
                    System.out.println(base.substring(0,i));
                    return base.substring(0,i);              
                }
            }
        }
        return base;
    }

    public static boolean isValidSign(String s){
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {   
            s = s.replace("[]", "");
            s = s.replace("()", "");
            s = s.replace("{}", "");
        }

        return s.isEmpty();
    }

    public static boolean isSubSecuence(List<Integer> listA, List<Integer> listB){ //A[1,2,3,4,5]   B[1,3,5]
        int count = 0;
        for(int nA: listA){
            if(nA == listB.get(count)){
                count++;
            }
            if (count == listB.size()){
                System.out.println(true);
                return true;
            }
        }
        System.out.println(false);
        return false;
    }

    public static int romans(String l){
        int result = 0;
        switch (l) {
            case "I":
                result = 1;
                break;
            case "V":
                result = 5;
                break;
            case "X":
                result = 10;
                break;
            case "L":
                result = 50;
                break;
            case "C":
                result = 100;
                break;
            case "D":
                result = 500;
                break;
            case "M":
                result = 1000;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    public static String unidad(int numero){
        String result = "";
        switch (numero) {
            case 1:
                result = "uno";
            break;
            case 2:
                result = "dos";
            break;
            case 3:
                result = "tres";
            break;
            case 4:
                result = "cuatro";
            break;
            case 5:
                result = "cinco";
            break;
            case 6:
                result = "seis";
            break;
            case 7:
                result = "siete";
            break;
            case 8:
                result = "ocho";
            break;
            case 9:
                result = "nueve";
            break;
            default:
                result = "";
            break;
        }

        return result;
    }

    public static String decena(int numero){
        String result = "";
        switch (numero) {
            case 1:
                result = "diez";
            break;
            case 2:
                result = "veinte";
            break;
            case 3:
                result = "treinta";
            break;
            case 4:
                result = "cuarenta";
            break;
            case 5:
                result = "cincuenta";
            break;
            case 6:
                result = "sesenta";
            break;
            case 7:
                result = "setenta";
            break;
            case 8:
                result = "ochenta";
            break;
            case 9:
                result = "noventa";
            break;
            default:
                result = "";
            break;
        }
        return result;
    }

    public static String decenas(int numero){
        String result = "";
        switch (numero) {
            case 11:
                result = "once";
                break;
            case 12:
                result = "doce";
                break;
            case 13:
                result = "trece";
                break;
            case 14:
                result = "catorce";
                break;
            case 15:
                result = "quince";
                break;
            case 16:
                result = "dieciseis";
                break;
            case 17:
                result = "diecisiete";
                break;
            case 18:
                result = "dieciocho";
                break;
            case 19:
                result = "diecinueve";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
    

    public static String centena(int numero){
        String result = "";
        switch (numero) {
            case 1:
                result = "cien";
                break;
            case 5:
                result = "quinientos";
                break;
            case 9:
                result = "novecientos";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    public static void numberToString(int number){ 
        String nTexto = "";

        int unidad = number % 10;
        number = number / 10;
        nTexto = unidad(unidad);

        int decena = number % 10;
        number = number / 10;
        if (unidad == 0 && decena > 0){
            nTexto = decena(decena);
        } else if (decena == 1) {
            nTexto = decenas(10 + unidad);
        } else if (decena > 1) {
            nTexto = decena(decena) + " y " + nTexto;
        }

        int centena = number % 10;
        number = number / 10;
        if(centena!= 0 && centena != 1 && centena != 5 && centena != 9){
            nTexto = unidad(centena) + "cientos " + nTexto;
        } else if (centena == 1 || centena == 5 || centena == 9){
            nTexto = centena(centena) + " " + nTexto;
        }

        int miles = number % 10;
        number = number / 10;
        if (miles != 0){
            nTexto = unidad(miles) + " mil " + nTexto;
        }

        int diezMiles = number % 10;
        number = number / 10;
        if (miles == 0 && diezMiles > 0){
            nTexto = decena(diezMiles) + " mil " + nTexto;
        } else if (diezMiles == 1){
            nTexto = decenas(10 + miles) + " mil " + nTexto;
        } else if (diezMiles > 1){ //45500
            nTexto = decena(diezMiles) + " y " + nTexto;
        }

        int cienMiles = number % 10;
        number = number / 10;
        if(cienMiles != 0){
            nTexto = unidad(cienMiles) + " cientos " + nTexto;
        }

        int millones = number % 10;
        number = number / 10;
        if (millones != 0){
            if (millones == 1) {
                nTexto = unidad(millones) + " millon " + nTexto;
            }else {
                nTexto = unidad(millones) + " millones " + nTexto;
            }
        }
    
        System.out.println(nTexto);
    }
}