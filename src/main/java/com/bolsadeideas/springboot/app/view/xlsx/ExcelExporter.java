package com.bolsadeideas.springboot.app.view.xlsx;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    public void exportToExcel(List<Cliente> data, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

        // Crea la cabecera
        Row headerRow = sheet.createRow(0);
        String[] headers = {"idcliente", "Cuil", "Nombre", "Apellido", "Domicilio", "Localidad", "Provincia",
                            "Celular", "Otro_telefono","Email", "Situacion_laboral", "Dependencia", "Banco_cobro", "Canal",
                            "Estado", "Financiador", "Monto_Aprobado", "Monto Cuota", "Plan", "Observacion", "Fecha Inicio","Fecha Fin", "Vendedor"}; // Reemplaza con los nombres de tus columnas
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Escribe los datos en las filas
        int rowNum = 1;
            for (Cliente dataModel : data) {
            Row row = sheet.createRow(rowNum++);

            /* datos personales */

            row.createCell(0).setCellValue(dataModel.getIdCliente());
            row.createCell(1).setCellValue(dataModel.getCuil());
            row.createCell(2).setCellValue(dataModel.getNombre());
            row.createCell(3).setCellValue(dataModel.getApellido());
            row.createCell(4).setCellValue(dataModel.getDomicilio());
            row.createCell(5).setCellValue(dataModel.getLocalidad());
            row.createCell(6).setCellValue(dataModel.getProvincia());
            row.createCell(7).setCellValue(dataModel.getCelular());
            row.createCell(8).setCellValue(dataModel.getOtro_telefono());
            row.createCell(9).setCellValue(dataModel.getEmail());
            row.createCell(10).setCellValue(dataModel.getSituacion_laboral());
            row.createCell(11).setCellValue(dataModel.getDependencia());
            row.createCell(12).setCellValue(dataModel.getBanco_cobro());

            /* datos de la solicitud */

            row.createCell(13).setCellValue(dataModel.getCanal());
            row.createCell(14).setCellValue(dataModel.getEstado());
            row.createCell(15).setCellValue(dataModel.getFinanciador());


            /* montos */

                if(dataModel.getMonto_solicitado() == null){
                    dataModel.setMonto_solicitado(0f);
                }

                row.createCell(16).setCellValue(dataModel.getMonto_solicitado());

                if(dataModel.getMontoCuota() == null){
                    dataModel.setMontoCuota(0);
                }

                row.createCell(17).setCellValue(dataModel.getMontoCuota());




                if(dataModel.getPlan() == null){
                    dataModel.setPlan(0);
                }

                row.createCell(18).setCellValue(dataModel.getPlan());


                /*  otros */

                row.createCell(19).setCellValue(dataModel.getObservacion());

                row.createCell(20).setCellValue(dataModel.getCreateAt().toString());

                row.createCell(21).setCellValue(dataModel.getClosedAt().toString());

                row.createCell(22).setCellValue(dataModel.getVendedor());


        }

        // Guarda el archivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Cierra los recursos
        workbook.close();
    }
}
