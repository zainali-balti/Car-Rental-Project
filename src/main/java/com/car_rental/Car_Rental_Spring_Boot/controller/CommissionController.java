package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.dto.CommissionDTO;
import com.car_rental.Car_Rental_Spring_Boot.dto.TimeDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.CommissionService;
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
@RequestMapping("commission")
public class CommissionController {
    @Autowired
    private CommissionService commissionService;
    @Operation(summary = "Get all Commission Details Report")
    @GetMapping("get")
    public ResponseEntity<byte[]> getCommission(@RequestBody TimeDTO timeDto) {
        ResponseEntity<List<CommissionDTO>> responseEntity =
                commissionService.getAll(timeDto);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


        List<CommissionDTO> commissionDTOS = responseEntity.getBody();
        byte[] pdfReport = generatePdfReport(commissionDTOS);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }

    private byte[] generatePdfReport(List<CommissionDTO> commissionDTOS) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            (document).open();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(90);

            // Adding table headers
            addTableHeader(table);

            // Adding rows
            for (CommissionDTO commissionDTO : commissionDTOS) {
                addRows(table,commissionDTO);
            }

            (document).add(table);
            (document).close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of( "Vehicle ID", "Commission", "Vehicle Name",
                        "Color","Owner Name")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table,CommissionDTO commissionDTO) {
        table.addCell(String.valueOf(commissionDTO.getId()));
        table.addCell(String.valueOf(commissionDTO.getCommission()));
        table.addCell(commissionDTO.getVehicleName());
        table.addCell(commissionDTO.getColor());
        table.addCell(commissionDTO.getOwnerName());
    }


}