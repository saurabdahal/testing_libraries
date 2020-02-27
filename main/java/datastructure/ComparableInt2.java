//package datastructure;
///*					Program-8 (Partial Solution)
//	(Implementing the Comparable Interface and defining two compareTo() methods)
//**/
//
//import java.util.Scanner;
//import java.util.Arrays;
//import java.util.InputMismatchException;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.io.FileNotFoundException;	// a subclass of IOException
//
//public class ComparableInt2 implements Comparable{
//
//        ////////////////////////////////////////////////////
//        public int compareTo (Object givenObject)
//		/*	Two different definitions here. One for sorting by lastName and the other by SSN.
//		This approach requires running the program twice using one different definition each time.
//		You can still produce one single output file by appending the second run results to the first.
//		First definition by SSN
//		*/
//        {
///*		if (givenObject == null)	// make sure givenObject does exist
//			{
//			System.out.println ("ERROR:::ABORTED:::A NON-EXISTING OBJECT BEING COMPARED:::");
//			System.exit (0);
//			}
//		if (getClass() == givenObject.getClass())	// same class
//			{
//			ComparableInt2 other = (ComparableInt2) givenObject;
//		//	return ssn.compareTo (other.ssn);	// compile-error:"long cannot be dereferenced"
//			if (ssn > other.ssn) return 1;
//			if (ssn < other.ssn) return -1;
//			else return 0;
//			}
//		else	// incomparable objects
//			{
//			System.out.println ("ERROR:::INCOMPARABLE OBJECTS:::");
//			System.exit(0);
//		return 0;	// unreachable and so useless. But needed to avoid an error: Missing return statement.
//			}	// end of incomparable objects.
//**/
/////////////////////Second definition to sort by lastName now///////////////////////
/////////////////////////////////////////////////////////////////////////////////////
//            if (givenObject == null)	// make sure givenObject does exist
//            {
//                System.out.println ("ERROR:::ABORTED:::A NON-EXISTING OBJECT BEING COMPARED:::");
//                System.exit (0);
//                return 0;
//            }
//            else
//////////////////////////////////////////////////////////////////////////////
//                if (getClass() == givenObject.getClass())	// same class
//                {
//                    ComparableInt2 other = (ComparableInt2) givenObject;
//                    return lastName.compareTo (other.lastName);
//                }
//                else	//	incomparable objects being compared
//                {
//                    System.out.println ("ERROR:::INCOMPARABLE OBJECTS:::");
//                    System.exit(0);
//                    return 0;	// Unreachable but needed to avoid an error: Missing return statement
//                }
//
//        }	//	end of compareTo() method by SSN (or by lastName)
//        ///////////////////////////////////////////////////////////////
//        public String toString ()
//        {
//            return (lastName + ":::" + id + ":::" + ssn);
//        } 	//	end of toString() method
//
//        /////////////////////////////////////////////////////
//        public static void main (String [] args)
//        {
//            int inputCount = 0;
//            String name = "null";	// just arbitrary
//            int id = 0;
//            long SSN = 0;
//            int temp = 0;
//            final int MAX = 1000;
//            ComparableInt  [] students1 = new ComparableInt  [MAX];
//            ComparableInt2 [] students2 = new ComparableInt2 [MAX];
//            PrintWriter outputStream = null;
//            Scanner inputStream = null;
//            try
//            {
//                outputStream = new PrintWriter (new FileOutputStream ("Program8.out", true));
//                inputStream  = new Scanner     (new FileInputStream  ("Program8.inp"));
//            }
//            catch (FileNotFoundException e)
//            {
//                outputStream.println ("Exception:::" + e.getMessage() + ":::Program terminated");
//                System.exit(0);
//            }	// end of catch block
//            while (inputStream.hasNext())
//            {
//                name = inputStream.next();
//                id   = inputStream.nextInt();
//                SSN  = inputStream.nextLong();
//                students1 [inputCount] = new ComparableInt  (name, id, SSN);
//                students2 [inputCount] = new ComparableInt2 (name, id, SSN);
//                inputCount++;
//            }	// end of all students inputs
//            //  Time to print original inputs and sorted results
//            ////////////////////////////////////////////////////
//            outputStream.println ("\nORIGINAL STUDENT DATA UNSORTED\n");
//            for (int times = 0; times < inputCount; times++)
//            {
//                temp = times+1;
//                outputStream.printf ("NUMBER:::%3d:::", temp);
//                outputStream.println (students1[times]);
//                System.out.printf ("NUMBER:::%3d:::", temp);
//                System.out.println (students1[times]);
//            }
//            //	print results of sorting by lastnames
//            ////////////////////////////////////////////////////
//
//            outputStream.println ("\nSTUDENT DATA SORTED BY LASTNAME\n");
//            Arrays.sort (students2, 0, inputCount);  	// sorting by lastName
//            for (int times = 0; times < inputCount; times++)
//            {
//                temp = times+1;
//                outputStream.printf ("NUMBER:::%3d:::", temp);
//                outputStream.println (students2[times]);
//            }
//            //	print results of sorting by SSN
//            ////////////////////////////////////////////////////
//
//            outputStream.println ("\nSTUDENT DATA SORTED BY SSN\n");
//            Arrays.sort (students2, 0, inputCount);	// sorting by SSN
////		Arrays.sort (students2);	//	THIS MAY throw NullPointerException as the actual array
//            //	length is unknown (or undeclared)
//            for (int times = 0; times < inputCount; times++)
//            {
//                temp = times+1;
//                outputStream.printf ("NUMBER:::%3d:::", temp);
//                outputStream.println (students2[times]);
//            }
//
//            ////////////////////////////////////////////////////////////////
//            //  Time to finish
//            inputStream.close();
//            outputStream.println ("\nPROGRAM SUCCESSFULLY PROCESSED " + temp + " STUDENTS BEFORE TERMINATING\n");
//            outputStream.close();
//        }	// end of main()
//}		// end of class ComparableInt2
//