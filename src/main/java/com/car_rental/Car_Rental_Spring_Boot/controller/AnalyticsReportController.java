package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.dto.AnalyticsReportDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.AnalyticsReportService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("analytics")
public class AnalyticsReportController {
    @Autowired
    private AnalyticsReportService analyticsReportService;

    @Operation(summary = "Get all analytical reports")
    @GetMapping("get")
    public ResponseEntity<byte[]> getAnalyticReport() {
        ResponseEntity<List<AnalyticsReportDTO>> responseEntity =
                analyticsReportService.getAll();
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        List<AnalyticsReportDTO> analyticsReportDTOS = responseEntity.getBody();
        byte[] pdfReport = generatePdfReport(analyticsReportDTOS);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }

    private byte[] generatePdfReport(List<AnalyticsReportDTO> analyticsReportDTOS) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            (document).open();

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(90);

            // Adding table headers
            addTableHeader(table);

            // Adding rows
            for (AnalyticsReportDTO analyticsReportDTO : analyticsReportDTOS) {
                addRows(table,analyticsReportDTO);
            }

            (document).add(table);
            (document).close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of( "ID MRO", "VName MRO","OName MRO","MCommission",
                "ID LRO", "VName LRO","OName LRO","LCommission")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table,AnalyticsReportDTO analyticsReportDTO) {
        table.addCell(String.valueOf(analyticsReportDTO.getMId()));
        table.addCell(analyticsReportDTO.getVehicleNameMostRentOut());
        table.addCell(analyticsReportDTO.getOwnerNameMostRentOut());
        table.addCell(String.valueOf(analyticsReportDTO.getMostCommission()));
        table.addCell(String.valueOf(analyticsReportDTO.getLId()));
        table.addCell(analyticsReportDTO.getVehicleNameLeastRentOut());
        table.addCell(analyticsReportDTO.getOwnerNameLeastRentOut());
        table.addCell(String.valueOf(analyticsReportDTO.getLeastCommission()));
    }
}
