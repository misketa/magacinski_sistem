package com.ftn.magacin.service.impl;

import com.ftn.magacin.model.FakturaPrijem;
import com.ftn.magacin.model.Otpremnica;
import com.ftn.magacin.model.Predmet;
import com.ftn.magacin.repository.PredmetRepository;
import com.ftn.magacin.service.DocumentService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    PredmetRepository predmetRepository;

    @Override
    public ByteArrayInputStream lagerLista(Long id) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Predmet p = predmetRepository.getOne(id);
            List<FakturaPrijem> prijemi = p.getPrijemi().stream().filter(o -> o.getStorno() == null || o.getStorno().equals(Boolean.FALSE)).collect(Collectors.toList());
            List<Otpremnica> otpremnice = p.getOtpremnice().stream().filter(o -> o.getStorno() == null || o.getStorno().equals(Boolean.FALSE)).collect(Collectors.toList());

            Paragraph paragraph1 = new Paragraph();
            paragraph1.add(
                    new Paragraph("Predmet: " + p.getNaziv(), new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD))
            );
            praznaLinija(paragraph1, 1);
            paragraph1.add(new Paragraph("Pocetno stanje: " + p.getPocetnoStanje()));
            paragraph1.add(new Paragraph("Kolicina na lageru: " + p.getKolicinaNaLageru()));
            praznaLinija(paragraph1, 1);

            paragraph1.add(new Paragraph("Prijemi: "));
            praznaLinija(paragraph1, 1);

            PdfPTable table1 = new PdfPTable(6);
            table1.setWidthPercentage(100);
            table1.setWidths(new int[]{1, 3, 3, 4, 5, 6});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Predmet", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Kolicina", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cena", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Ukupna cena", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Firma", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table1.addCell(hcell);

            for (FakturaPrijem f : prijemi) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(f.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(f.getPredmet().getNaziv()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(f.getKolicina().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(f.getCena().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(f.getUkupnaCena().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table1.addCell(cell);

                cell = new PdfPCell(new Phrase(f.getFirma()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table1.addCell(cell);
            }

            Paragraph paragraph2 = new Paragraph();
            paragraph2.add(new Paragraph("Otpremnice: "));
            praznaLinija(paragraph2, 1);

            PdfPTable table2 = new PdfPTable(6);
            table2.setWidthPercentage(100);
            table2.setWidths(new int[]{1, 3, 3, 4, 5, 6});


            PdfPCell hcell2;
            hcell2 = new PdfPCell(new Phrase("Id", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            hcell2 = new PdfPCell(new Phrase("Predmet", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            hcell2 = new PdfPCell(new Phrase("Kolicina", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            hcell2 = new PdfPCell(new Phrase("Cena", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            hcell2 = new PdfPCell(new Phrase("Ukupna cena", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            hcell2 = new PdfPCell(new Phrase("Firma", headFont));
            hcell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(hcell2);

            for (Otpremnica o : otpremnice) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(o.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table2.addCell(cell);

                cell = new PdfPCell(new Phrase(o.getPredmet().getNaziv()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table2.addCell(cell);

                cell = new PdfPCell(new Phrase(o.getKolicina().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table2.addCell(cell);

                cell = new PdfPCell(new Phrase(o.getCena().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table2.addCell(cell);

                cell = new PdfPCell(new Phrase(o.getUkupnaCena().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table2.addCell(cell);

                cell = new PdfPCell(new Phrase(o.getFirma()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table2.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(paragraph1);
            document.add(table1);
            document.add(paragraph2);
            document.add(table2);

            document.close();
        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream blankoPopis() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Paragraph paragraph = new Paragraph();
            praznaLinija(paragraph, 1);

            paragraph.add(
                    new Paragraph("DOKUMENT ZA POPIS ROBE", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD))
            );

            praznaLinija(paragraph, 1);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Vrsta robe", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Kolicina", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Napomena", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (int i = 0; i < 100; i++) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Oznaka: "));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(" "));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(" "));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

            }

            Anchor anchor = new Anchor("Potpis: _________________________");

            Paragraph signature = new Paragraph(anchor);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(paragraph);
            document.add(table);
            document.add(signature);
        } catch (Exception ex) {
        }


        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream magacinskaKartica() {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<Predmet> predmetList = predmetRepository.findAll();
        try {
            Paragraph paragraph = new Paragraph();

            // We add one empty line
            praznaLinija(paragraph, 1);

            // Lets write a big header
            paragraph.add(
                    new Paragraph("Stanje u magacinu na dan " + new Date(), new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD))
            );

            praznaLinija(paragraph, 2);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 1, 3, 3 });

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Predmet", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Kolicina", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


            for (Predmet p : predmetList) {


                PdfPCell cell;

                cell = new PdfPCell(new Phrase(p.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(p.getNaziv()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(p.getKolicinaNaLageru().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(paragraph);
            document.add(table);

            document.close();
        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }



    private static void praznaLinija(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
