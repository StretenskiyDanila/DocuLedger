
package org.example.businesspack.exports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.window.models.tab.TabManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс PDFGenerator содержит методы для генерации PDF-отчета на основе данных
 * из окна.
 */
@Slf4j
public class PDFGenerator {

	/**
	 * Генерирует PDF-отчет на основе данных из таблицы и формы.
	 *
	 * @param tabManager менеджер таблицы и формы
	 * @param filePath   путь к файлу PDF
	 */
	public void generatePdfItext(TabManager<DataWorkDto> tabManager, String filePath) {
		if (filePath == null || filePath.trim().isEmpty()) {
			log.error("Ошибка: путь к файлу не указан!");
			return;
		}

		List<ComboBox<PersonDto>> listComboBox = tabManager.getComboBoxManagers().stream()
				.map(manager -> manager.getComboBox()).toList();
		TableView<DataWorkDto> tableView = tabManager.getTableManager().getTable();
		DatePicker datePicker = tabManager.getDataPicker();

		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			Document document = new Document(PageSize.A4);
			PdfWriter.getInstance(document, fos);
			document.open();

			Font titleFont = getRussianFont(16, Font.BOLD);
			Paragraph title = new Paragraph("Отчет", titleFont);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(new Paragraph("\n"));

			Font font = getRussianFont(12, Font.NORMAL);

			for (Map.Entry<String, String> entry : extractFormData(listComboBox, datePicker).entrySet()) {
				document.add(new Paragraph(entry.getKey() + ": " + entry.getValue(), font));
			}
			document.add(new Paragraph("\n"));

			List<List<String>> tableData = extractTableData(tableView);
			PdfPTable table = new PdfPTable(tableData.get(0).size());
			table.setWidthPercentage(100);

			tableData.stream().flatMap(Collection::stream).forEach(cell -> {
				PdfPCell cell1 = new PdfPCell(new Phrase(cell, font));
				cell1.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell1);
			});
			// TODO: выравнить значения по центру и автоширина столбцов

			document.add(table);

			document.close();
			System.out.println("PDF создан по пути: " + filePath);
			//return filePath;
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Извлекает все данные кроме таблицы.
	 *
	 * @param listComboBox список ComboBox
	 * @param datePicker   DatePicker
	 * @return карта с данными формы
	 */
	private Map<String, String> extractFormData(List<ComboBox<PersonDto>> listComboBox, DatePicker datePicker) {
		Map<String, String> data = new LinkedHashMap<>();
		listComboBox.forEach(
				comboBox -> data.put(comboBox.getId(), comboBox.getSelectionModel().getSelectedItem().getName()));

		data.put("Дата", datePicker.getValue() != null ? datePicker.getValue().toString() : "");
		return data;
	}

	/**
	 * Извлекает данные из таблицы.
	 *
	 * @param tableView таблица
	 * @return список списков строк с данными таблицы
	 */
	private List<List<String>> extractTableData(TableView<DataWorkDto> tableView) {
		List<List<String>> tableData = new ArrayList<>();
		tableData.add(tableView.getColumns().stream().map(column -> column.getText()).toList());

		tableView.getItems().forEach(item -> {
			List<String> row = List.of(item.getNameParameter(), item.getUnitMeasParameter(),
					item.getCountParameter().toString(), item.getPriceParameter().toString(),
					item.getVatParameter().toString(), item.getSummaParameter().toString(), item.getGroupParameter());
			tableData.add(row);
		});
		return tableData;
	}

	/**
	 * Получение шрифта из файла.
	 *
	 * @param size  размер шрифта
	 * @param style стиль шрифта
	 * @return сформированный шрифт
	 */
	private Font getRussianFont(float size, int style) {
		try {
			String path = Objects.requireNonNull(getClass().getResource("/org/example/businesspack/font/TIMES.TTF")).getPath();
			BaseFont baseFont = BaseFont.createFont(path,
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			return new Font(baseFont, size, style);
		} catch (DocumentException | IOException e) {
			log.error("Ошибка загрузки шрифта: " + e.getMessage());
			return new Font(Font.FontFamily.HELVETICA, size, style);
		}
	}

}