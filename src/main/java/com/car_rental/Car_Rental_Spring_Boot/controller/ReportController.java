package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.dto.ReportDTO;
import com.car_rental.Car_Rental_Spring_Boot.dto.TimeDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.ReportService;
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
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "Get all the Reports")
    @GetMapping("get")
    public ResponseEntity<byte[]> getMonthlyReport(@RequestBody TimeDTO timeDto) {
        ResponseEntity<List<ReportDTO>> responseEntity = reportService.getAll(timeDto);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<ReportDTO> reportDTOS = responseEntity.getBody();
        byte[] pdfReport = generatePdfReport(reportDTOS);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }

    private byte[] generatePdfReport(List<ReportDTO> reportDTOS) {
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
            for (ReportDTO reportDTO : reportDTOS) {
                addRows(table, reportDTO);
            }

            (document).add(table);
            (document).close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Customer ID","CustomerName", "Vehicle ID"
                        ,"VehicleName","Booking Date", "Price", "Status")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, ReportDTO reportDTO) {
        table.addCell(String.valueOf(reportDTO.getId()));
        table.addCell(String.valueOf(reportDTO.getCustomerId()));
        table.addCell(reportDTO.getCustomerName());
        table.addCell(String.valueOf(reportDTO.getVehicleId()));
        table.addCell(reportDTO.getVehicleName());
        table.addCell(String.valueOf(reportDTO.getBookingDate()));
        table.addCell(String.valueOf(reportDTO.getPrice()));
        table.addCell(reportDTO.getStatus());
    }
}
