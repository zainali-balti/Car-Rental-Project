package com.car_rental.Car_Rental_Spring_Boot.controller;

import com.car_rental.Car_Rental_Spring_Boot.dto.CarAvailableDTO;
import com.car_rental.Car_Rental_Spring_Boot.service.CarAvailableService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("carAvailable")
public class CarAvailableController {
    @Autowired
    private CarAvailableService carAvailableService;
    @Operation(summary = "Get all Car Available at this Time ")
    @GetMapping("get")
    public ResponseEntity<byte[]> carAvailableReport() {
        ResponseEntity<List<CarAvailableDTO>> responseEntity =
                carAvailableService.getAll();
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<CarAvailableDTO> carAvailableDTOS = responseEntity.getBody();
        byte[] pdfReport = generatePdfReport(carAvailableDTOS);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfReport);
    }

    private byte[] generatePdfReport(List<CarAvailableDTO> carAvailableDTOS) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            (document).open();

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(90);

            // Adding table headers
            addTableHeader(table);

            // Adding rows
            for (CarAvailableDTO carAvailableDTO : carAvailableDTOS) {
                addRows(table,carAvailableDTO);
            }

            (document).add(table);
            (document).close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("ID", "Vehicle Name","Owner Name" ,"Model", "Brand", "Color")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setPhrase(new Phrase(columnTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, CarAvailableDTO carAvailableDTO) {
        table.addCell(String.valueOf(carAvailableDTO.getId()));
        table.addCell(carAvailableDTO.getVehicleName());
        table.addCell(carAvailableDTO.getOwnerName());
        table.addCell(String.valueOf(carAvailableDTO.getModel()));
        table.addCell(carAvailableDTO.getBrand());
        table.addCell(carAvailableDTO.getColor());
    }
}
