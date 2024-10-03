public class Main {

  public static void main(String[] args) {

    // value a, b, and c
    double a = 2.3, b = 4, c = 5.6;
    double root1, root2;

    // calculate the discriminant (b2 - 4ac)
    double discriminant = b * b - 4 * a * c;

    // check if discriminant is greater than 0
    if (discriminant > 0) {

      // two real and distinct roots
      root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
      root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

      System.out.format("root1 = %.2f and root2 = %.2f", root1, root2);
    }

    // check if discriminant is equal to 0
    else if (discriminant == 0) {

      // two real and equal roots
      // discriminant is equal to 0
      // so -b + 0 == -b
      root1 = root2 = -b / (2 * a);
      System.out.format("root1 = root2 = %.2f;", root1);
    }

    // if discriminant is less than zero
    else {

      // roots are complex number and distinct
      double real = -b / (2 * a);
      double imaginary = Math.sqrt(-discriminant) / (2 * a);
      System.out.format("root1 = %.2f+%.2fi", real, imaginary);
      System.out.format("\nroot2 = %.2f-%.2fi", real, imaginary);
    }
  }
}
