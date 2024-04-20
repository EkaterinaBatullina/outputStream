/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package a.outputstream;

import java.util.HashMap;
import java.util.Map;    
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author ekaterina
 */
public class OutputStream {

    public static void main(String[] args) throws IOException {
        
//1. Написать программу, объединяющую 2 файла в один.
// Программа должна принимать 3 параметра командной строки file1 file2 fileResult 
//(Использовать FileInputStream и FileOutputStream)
   
        if (args.length != 3) {
            return;
        }

        String file1Path = args[0];
        String file2Path = args[1];
        String fileResultPath = args[2];

        try (FileInputStream file1 = new FileInputStream(file1Path);
             FileInputStream file2 = new FileInputStream(file2Path);
             FileOutputStream file1file2 = new FileOutputStream(fileResultPath)) {

            int data;
            while ((data = file1.read()) != -1) {
                file1file2.write(data);
            }

            while ((data = file2.read()) != -1) {
                file1file2.write(data);
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


//2. Прочитать файл по-символьно, и вывести талицу: 
//буква - количество вхождений (учитывать только имеющиеся в наличие символы)
        if (args.length != 1) {
            return;
        }      
        
        String fileName = args[0];
        Map<Character, Integer> letterCount = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(fileName))) {
            int data;
            while ((data = fileInputStream.read()) != -1) {
                char letter = (char) data;
                letterCount.put(letter, letterCount.getOrDefault(letter, 0) + 1); 
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Буква - Количество вхождений");
        for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

//3. создать файл в формате:
// 1-е 4 байта - число N (длина сообщения в байтах)
//следующие N байтов - сообщение
        if (args.length != 1) {
            return;
        }      
        
        String fileName = args[0];
        try (DataOutputStream fileOutputStream = new DataOutputStream(new FileOutputStream(fileName))) {
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine(); 
            int messageLength = message.length(); 
            fileOutputStream.writeInt(messageLength); 
            fileOutputStream.write(message.getBytes());
            System.out.println("Файл " + fileName + " создан");
            
 //4. Прочитать сообщение из созданного файла и вывести его длину, прочитанную из первых 4 байтов
            FileInputStream fileInputStream = new FileInputStream(fileName);
            int data;
            int counter = 0;
            boolean flag = false;
            System.out.print("Длина сообщения: ");
            while ((data = fileInputStream.read()) != -1) {
                counter++;
                if (counter <= 4) {
                    if (data != 0 || (data == 0 && flag == true)) {
                        flag = true;
                        System.out.print(data); 
                    }   
                }
            }
        } catch (IOException e) {
            System.out.println( e.getMessage());
        }


    }
  
}

        
