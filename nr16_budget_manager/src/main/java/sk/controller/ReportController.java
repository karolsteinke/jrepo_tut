package sk.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import sk.dto.ReportDto;
import sk.service.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    //e.g.: http://localhost:8080/reports/monthly?month=4&year=2025
    @GetMapping("/monthly")
    public String showMonthlyReport(@RequestParam int month, @RequestParam int year, Model model) {
        ReportDto report = reportService.generateMonthlyReport(month, year);
        model.addAttribute("report", report);
        return "reports/monthly";
    }

    //def.:
    @GetMapping("/monthly/csv")
    public void exportMonthlyReportToCsv(@RequestParam int month, @RequestParam int year, HttpServletResponse response) throws IOException { 
        ReportDto report = reportService.generateMonthlyReport(month, year);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        reportService.writeCsvReport(report, response.getWriter());
    }
}
