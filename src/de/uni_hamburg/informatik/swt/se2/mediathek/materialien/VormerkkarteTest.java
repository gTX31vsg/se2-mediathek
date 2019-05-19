package de.uni_hamburg.informatik.swt.se2.mediathek.materialien;

import de.uni_hamburg.informatik.swt.se2.mediathek.fachwerte.Kundennummer;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.CD;
import de.uni_hamburg.informatik.swt.se2.mediathek.materialien.medien.Medium;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class VormerkkarteTest {
    private Vormerkkarte _vormerkkarte;
    private LinkedList<Kunde> _kunden;
    private static Kunde KUNDE1 = new Kunde(new Kundennummer(123456), "Vorname1", "Nachname1");
    private static Kunde KUNDE2 = new Kunde(new Kundennummer(123457), "Vorname2", "Nachname2");
    private static Kunde KUNDE3 = new Kunde(new Kundennummer(123458), "Vorname3", "Nachname3");
    private static Kunde KUNDE4 = new Kunde(new Kundennummer(123459), "Vorname4", "Nachname4");
    private Medium _medium;

    public VormerkkarteTest()
    {
        _kunden = new LinkedList<>();
        _kunden.add(KUNDE1);
        _kunden.add(KUNDE2);
        _kunden.add(KUNDE3);

        _medium = new CD("CD1", "Kommentar1", "Interpret1", 123);
        _vormerkkarte = new Vormerkkarte(_kunden, _medium);
    }

    @Test
    public void testKonstruktor()
    {
        assertEquals(_medium, _vormerkkarte.getMedium());
        assertEquals(_kunden, _vormerkkarte.getKunden());
    }

    @Test
    public void testAddKunde()
    {
        // copy vormerkkarte
        Vormerkkarte vormerkkarte = new Vormerkkarte(_medium);

        // add KUNDE1
        assertFalse(vormerkkarte.istVorgemerkt(KUNDE1));
        assertEquals(0, vormerkkarte.anzahlVormerker());
        assertTrue(vormerkkarte.vormerkenMoeglich(KUNDE1));
        vormerkkarte.addKunde(KUNDE1);
        assertTrue(vormerkkarte.istVorgemerkt(KUNDE1));
        assertEquals(KUNDE1, vormerkkarte.getErstenVormerker());

        // add KUNDE2
        assertFalse(vormerkkarte.istVorgemerkt(KUNDE2));
        assertEquals(1, vormerkkarte.anzahlVormerker());
        assertTrue(vormerkkarte.vormerkenMoeglich(KUNDE2));
        vormerkkarte.addKunde(KUNDE2);
        assertTrue(vormerkkarte.istVorgemerkt(KUNDE2));
        assertEquals(KUNDE2, vormerkkarte.getVormerkerAtPosition(1));

        // add KUNDE3
        assertFalse(vormerkkarte.istVorgemerkt(KUNDE3));
        assertEquals(2, vormerkkarte.anzahlVormerker());
        assertTrue(vormerkkarte.vormerkenMoeglich(KUNDE3));
        vormerkkarte.addKunde(KUNDE3);
        assertTrue(vormerkkarte.istVorgemerkt(KUNDE3));
        assertEquals(KUNDE3, vormerkkarte.getVormerkerAtPosition(2));

        // try add KUNDE4
        assertFalse(vormerkkarte.istVorgemerkt(KUNDE4));
        assertEquals(3, vormerkkarte.anzahlVormerker());
        assertFalse(vormerkkarte.vormerkenMoeglich(KUNDE4));
    }

    @Test
    public void testRemoveErsteVormerkung()
    {
        // copy vormerkkarte
        Vormerkkarte vormerkkarte = new Vormerkkarte(new LinkedList<>(_kunden), _medium);

        assertTrue(vormerkkarte.istVorgemerkt(KUNDE1));
        assertEquals(KUNDE1, vormerkkarte.getErstenVormerker());
        assertEquals(KUNDE2, vormerkkarte.getVormerkerAtPosition(1));
        assertEquals(KUNDE3, vormerkkarte.getVormerkerAtPosition(2));

        assertEquals(3, vormerkkarte.anzahlVormerker());
        vormerkkarte.removeErsteVormerkung();
        assertEquals(2, vormerkkarte.anzahlVormerker());

        assertFalse(vormerkkarte.istVorgemerkt(KUNDE1));
        assertNotEquals(KUNDE1, vormerkkarte.getErstenVormerker());
        assertEquals(KUNDE2, vormerkkarte.getErstenVormerker());
        assertEquals(KUNDE3, vormerkkarte.getVormerkerAtPosition(1));

        vormerkkarte.removeErsteVormerkung();
        assertEquals(1, vormerkkarte.anzahlVormerker());

        assertFalse(vormerkkarte.istVorgemerkt(KUNDE2));
        assertNotEquals(KUNDE2, vormerkkarte.getErstenVormerker());
        assertEquals(KUNDE3, vormerkkarte.getErstenVormerker());
    }
}
