package utils;

import java.util.Objects;

public class ComplexNumber {
    private Double real;
    private Double imaginary;

    public ComplexNumber(Double real, Double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }


    public Double getReal() {
        return real;
    }

    public void setReal(Double real) {
        this.real = real;
    }

    public Double getImaginary() {
        return imaginary;
    }

    public void setImaginary(Double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return real == that.real &&
                imaginary == that.imaginary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        if (imaginary < 0)
            return "" + real + "-" + imaginary + "i ";
        else
            return "" + real + "+" + imaginary + "i ";

    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }


    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        final Double real = a.getReal() * b.getReal() - b.getImaginary() * a.getImaginary();
        final Double imaginary = a.getImaginary() * b.getReal() + a.getReal() * b.getImaginary();
        return new ComplexNumber(real, imaginary);
    }

    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
        final ComplexNumber complexNumber = multiply(a, getConjugate(b));
        final Double numitor = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
        return new ComplexNumber(complexNumber.getReal() / numitor, complexNumber.getImaginary() / numitor);
    }

    public static ComplexNumber getConjugate(ComplexNumber a) {
        return new ComplexNumber(a.getReal(), -a.getImaginary());
    }

    public static ComplexNumber evaluteExpresion(ComplexNumber a, ComplexNumber b) {
        //expre=ab/(a+b);
        final ComplexNumber numarator = multiply(a, b);
        final ComplexNumber numitor = add(a, b);
        return divide(numarator, numitor);
    }
}
