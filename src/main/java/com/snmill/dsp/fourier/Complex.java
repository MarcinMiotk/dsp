package com.snmill.dsp.fourier;

import java.math.BigDecimal;

class Complex {

    final Real real;
    final Img img;

    public Complex(Real real, Img img) {
        this.real = real;
        this.img = img;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Rel=").append(real.getValue()).append(" Img=").append(img.getValue());
        return sb.toString();
    }

    public Complex(BigDecimal real, BigDecimal img) {
        this.real = new Real(real);
        this.img = new Img(img);
    }

    static class Real {

        final BigDecimal value;

        public Real(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
    }

    static class Img {
        final BigDecimal value;

        public Img(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
    }
}
