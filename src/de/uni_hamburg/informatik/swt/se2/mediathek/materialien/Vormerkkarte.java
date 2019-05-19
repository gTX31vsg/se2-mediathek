package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Mit Hilfe von Vormerkkarten werden beim Vormerken eines Mediums alle relevanten
 * Daten notiert.
 *
 * Sie beantwortet die folgenden Fragen: Welches Medium wurde vorgemerkt? Wer
 * hat das Medium vorgemerkt?
 *
 * Wenn Medien zurück gegeben werden, kann die zugehörige Verleihkarte entsorgt
 * werden. Um die Verwaltung der Karten kümmert sich der VerleihService
 *
 * @version SoSe 2019
 */
public class Vormerkkarte
{
    // Maximale Kunden pro Vormerkkarte
    public static final int MAX_KUNDEN = 3;

    // Eigenschaften einer Vormerkkarte
    private final List<Kunde> _kunden;
    private final Medium _medium;

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     *
     * @param kunden Eine Liste mit Kunden, die das Medium vorgemerkt haben.
     * @param medium Ein vorgemerktes Medium.
     *
     * @require kunden != null
     * @require medium != null
     *
     * @ensure #getKunden() == kunden
     * @ensure #getMedium() == medium
     */
    public Vormerkkarte(LinkedList<Kunde> kunden, Medium medium)
    {
        _kunden = kunden;
        _medium = medium;
    }

    /**
     * Initialisert eine neue Vormerkkarte mit den gegebenen Daten.
     *
     * @param medium Ein vorgemerktes Medium.
     *
     * @require medium != null
     *
     * @ensure #getMedium() == medium
     */
    public Vormerkkarte(Medium medium)
    {
        _kunden = new LinkedList<>();
        _medium = medium;
    }

    /**
     * Gibt den ersten Kunden zurück, welcher das Medium vorgemerkt hat.
     *
     * @return Den Kunden.
     *
     * @ensure result != null
     */
    public Kunde getErstenVormerker()
    {
        return ((LinkedList<Kunde>) _kunden).getFirst();
    }

    /**
     * Gibt einen Kunden an einer besimmten Position zurück, welcher das Medium vorgemerkt hat.
     *
     * @param position Position des Kunden.
     *
     * @return Den Kunden.
     *
     * @ensure result != null
     */
    public Kunde getVormerkerAtPosition(int position)
    {
        return _kunden.get(position);
    }

    /**
     * Prüft ob ein Kunde an erster Position steht.
     *
     * @return true, wenn an erster Position, sonst false
     *
     * @ensure result != null
     */
    public boolean istErsterVorkermer(Kunde kunde)
    {
        return getErstenVormerker().equals(kunde);
    }

    /**
     * Fügt einen Kunden hinzu.
     *
     * @param kunde Der Kunde.
     */
    public void addKunde(Kunde kunde)
    {
        _kunden.add(kunde);
    }

    /**
     * Entfernt den ersten vorgemerkten Kunden.
     */
    public void removeErsteVormerkung()
    {
        ((LinkedList<Kunde>) _kunden).removeFirst();
    }

    /**
     * Prüft ob ein Kunde vorgemerkt ist.
     *
     * @param kunde Der Kunde.
     *
     * @return true, wenn vorgemerkt, sonst false
     *
     * @ensure result != null
     */
    public boolean istVorgemerkt(Kunde kunde)
    {
        return _kunden.contains(kunde);
    }

    /**
     * Prüft ob ein Kunde vorgemerkt werden kann.
     *
     * @param kunde Der Kunde.
     *
     * @return true, wenn Vormerkung möglich, sonst false
     *
     * @ensure result != null
     */
    public boolean vormerkenMoeglich(Kunde kunde)
    {
        return anzahlVormerker() < MAX_KUNDEN && !istVorgemerkt(kunde);
    }

    /**
     * Gibt die Anzahl der vorgemerkten Kunden zurück.
     *
     * @return Die Anzahl.
     *
     * @ensure result != null
     */
    public int anzahlVormerker()
    {
        return _kunden.size();
    }

    /**
     * Gibt eine String-Darstellung der Vormerkkarte (enhält Zeilenumbrüche)
     * zurück.
     *
     * @return Eine formatierte Stringrepäsentation der Vormerkkarte. Enthält
     *         Zeilenumbrüche.
     *
     * @ensure result != null
     */
    public String getFormatiertenString()
    {
        String formatiert = _medium.getFormatiertenString() + " vorgemerkt für:\n";

        for (Kunde kunde : _kunden) {
            formatiert += kunde.getFormatiertenString() + "\n";
        }

        return formatiert;
    }

    /**
     * Gibt eine Liste mit allen vorgemerkten Kunden zurück.
     *
     * @return Eine Liste mit allen vorgemerkten Kunden.
     *
     * @ensure result != null
     */
    public List<Kunde> getKunden()
    {
        return _kunden;
    }

    /**
     * Gibt das Medium zurück.
     *
     * @return Das Medium.
     *
     * @ensure result != null
     */
    public Medium getMedium()
    {
        return _medium;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_kunden == null) ? 0 : _kunden.hashCode());
        result = prime * result + ((_medium == null) ? 0 : _medium.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean result = false;
        if (obj instanceof Vormerkkarte)
        {
            Vormerkkarte other = (Vormerkkarte) obj;

            if (other.getKunden()
                    .equals(_kunden)
                    && other.getMedium()
                    .equals(_medium))

                result = true;
        }
        return result;
    }

    @Override
    public String toString()
    {
        return getFormatiertenString();
    }
}
