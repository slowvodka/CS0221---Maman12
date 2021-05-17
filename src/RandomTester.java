import java.io.*;
import java.util.Scanner;

public class RandomTester {
    // if you are using intelliJ - add "src/" before the file names:
    final static String randomDataFile = "src/random-data.txt";
    final static String answersFile = "src/answers.txt";

    final static int randomPointsCount = 3000;

    public static void main(String[] args) {

        try {
            Point[] pArr = getRandomPointsArr();
            Triangle[] tArr = new Triangle[pArr.length / 3];
            for (int i = 0; i < tArr.length; i++)
                tArr[i] = new Triangle(pArr[3 * i], pArr[3 * i + 1], pArr[3 * i + 2]);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(stream);
            PrintStream old = System.out;
            System.setOut(ps);

            for (int i = 0; i < tArr.length; i++) {
                String info = "triangle: " + tArr[i] + " the method ";

                System.out.println(info + "getPerimeter returns:" + tArr[i].getPerimeter());
                System.out.println(info + "getArea returns:" + tArr[i].getArea());
                System.out.println(info + "isIsosceles returns:" + tArr[i].isIsosceles());
                System.out.println(info + "isPythagorean returns:" + tArr[i].isPythagorean());
                System.out.println(info + "lowestPoint returns:" + tArr[i].lowestPoint());
                System.out.println(info + "highestPoint returns:" + tArr[i].highestPoint());
                System.out.println(info + "isLocated returns:" + tArr[i].isLocated());
                System.out.println(info + "equals (testing the same triangle) returns:" + tArr[i].equals(tArr[i]));

                Point centerPoint = new Point(pArr[2 * i + 5]);
                double radius = pArr[2 * i + 3].getX();
                System.out.println(info + "isContainedInCircle(" + centerPoint.getX() + ", " + centerPoint.getY() + ", " + radius + ") returns:" + tArr[i].isContainedInCircle(centerPoint.getX(), centerPoint.getY(), radius));

                if (i < tArr.length - 1) {
                    System.out.println("t1: " + tArr[i] + " t2: " + tArr[i + 1] + " the method equals returns:" + tArr[i].equals(tArr[i + 1]));
                    System.out.println("t1: " + tArr[i] + " t2: " + tArr[i + 1] + " the method isAbove returns:" + tArr[i].isAbove(tArr[i + 1]));
                    System.out.println("t1: " + tArr[i] + " t2: " + tArr[i + 1] + " the method isUnder returns:" + tArr[i].isUnder(tArr[i + 1]));
                    System.out.println("t1: " + tArr[i] + " t2: " + tArr[i + 1] + " the method isCongruent returns:" + tArr[i].isCongruent(tArr[i + 1]));
                }

                System.out.println(info + "getPoint1 returns:" + tArr[i].getPoint1());
                System.out.println(info + "getPoint2 returns:" + tArr[i].getPoint2());
                System.out.println(info + "getPoint3 returns:" + tArr[i].getPoint3());

                System.out.print("triangle: " + tArr[i] + " setPoint1 to " + pArr[i] + ", method toString returns:");
                tArr[i].setPoint1(pArr[i]);
                System.out.println(tArr[i]);

                System.out.print("triangle: " + tArr[i] + " setPoint2 to " + pArr[2 * i] + ", method toString returns:");
                tArr[i].setPoint2(pArr[2 * i]);
                System.out.println(tArr[i]);

                System.out.print("triangle: " + tArr[i] + " setPoint3 to " + pArr[3 * i] + ", method toString returns:");
                tArr[i].setPoint3(pArr[3 * i]);
                System.out.println(tArr[i]);
            }

            System.out.flush();
            System.setOut(old);

            String[] userOutputArr = stream.toString().split("\\r?\\n");

            Scanner reader = new Scanner(new File(answersFile));
            int good = 0, bad = 0;
            System.out.println("--------------------------------------");
            for (String userOutput : userOutputArr) {
                if (!reader.hasNextLine())
                    break;

                String originalOutput = reader.nextLine();
                if (compareAnswers(originalOutput, userOutput))
                    good++;
                else {
                    bad++;
                    System.out.println("A difference in output was found!");
                    System.out.println("Your output:" + System.lineSeparator() + userOutput);
                    System.out.println("My output:" + System.lineSeparator() + originalOutput);
                    System.out.println("--------------------------------------");
                }

            }

            System.out.println(System.lineSeparator() + "We have the same answers around: " + (double) good / (good + bad) * 100 + "%");


        } catch (Exception e) {
            System.out.println("-Error-");
            e.printStackTrace();
        }

    }

    private static Point[] getRandomPointsArr() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(randomDataFile));
            Point[] pointsArr = new Point[randomPointsCount];

            String line;
            int idx = 0;
            while ((line = reader.readLine()) != null) {
                String[] doublesStr = line.split("\\s+");
                pointsArr[idx++] = new Point(Double.parseDouble(doublesStr[0]), Double.parseDouble(doublesStr[1]));
            }
            reader.close();

            if (idx != randomPointsCount)
                throw new Exception("Error - couldn't get all points");
            return pointsArr;

        } catch (IOException | NumberFormatException e) {
            throw new Exception(e);
        }

    }

    private static boolean compareAnswers(String original, String current) {
        String originalAnswer = original.substring(original.indexOf("returns:") + "returns:".length());
        String currentAnswer = current.substring(current.indexOf("returns:") + "returns:".length());
        try {
            return Math.abs(Double.parseDouble(originalAnswer) - Double.parseDouble(currentAnswer)) < Triangle.EPSILON;
        } catch (NumberFormatException e) {
            return originalAnswer.equals(currentAnswer);
        }
    }

}
