package kanta;

/**
 * @author laura
 * @version 27.2.2021
 *
 */
public class Tarkistus {
    /**
     * Arvotaan satunnainen kokonaisluku v�lille [ala, yla]
     * @param ala arvonnan alaraja
     * @param yla arvonnan yl�raja
     * @return satunnainen luku v�lilt� [ala, yla]
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
}
