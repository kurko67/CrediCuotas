package com.bolsadeideas.springboot.app.view.xlsx;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("estadisticas/ver.xlsx")
public class EstadisticasXlsxView extends AbstractXlsView {


    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {

        Cliente cliente = (Cliente) map.get("cliente");
        Sheet sheet = workbook.createSheet();

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

        cell.setCellValue("Fecha de estadistica: ");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(cliente.getNombre());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(cliente.getEmail());

        //encadenando los metodos

        sheet.createRow(3).createCell(0).setCellValue("Movimientos");
        sheet.createRow(4).createCell(0).setCellValue("Movimientos 2");
        sheet.createRow(5).createCell(0).setCellValue("Movimientos 3");




    }
}
