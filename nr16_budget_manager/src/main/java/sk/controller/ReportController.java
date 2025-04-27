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

    //def.: request report object (from service) and add it to the model
    //def.: return "reports/monthly" view (showing report data)
    //e.g.: http://localhost:8080/reports/monthly?month=4&year=2025
    @GetMapping("/monthly")
    public String showMonthlyReport(@RequestParam int month, @RequestParam int year, Model model) {
        ReportDto report = reportService.generateMonthlyReport(month, year);
        model.addAttribute("report", report);
        return "reports/monthly";
    }

    //def.: request report object (from service) and add it to the model
    //def.: return "reports/yearly" view (showing report data)
    @GetMapping("/yearly")
    public String showYearlyReport(@RequestParam int year, Model model) {
        ReportDto report = reportService.generateYearlyReport(year);
        model.addAttribute("report", report);
        return "reports/yearly";
    }

    //def.: request report object (from service) and save it to HTTP response as CSV attachment
    @GetMapping("/monthly/csv")
    public void exportMonthlyReportToCsv(@RequestParam int month, @RequestParam int year, HttpServletResponse response) throws IOException { 
        ReportDto report = reportService.generateMonthlyReport(month, year); //writer.println() possibly throws IOException

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        reportService.writeCsvReport(report, response.getWriter()); //possibly throws IOException
    }

    //def.: request report object (from service) and save it to HTTP response as CSV attachment
    @GetMapping("/yearly/csv")
    public void exportYearlyReportToCsv(@RequestParam int year, HttpServletResponse response) throws IOException { 
        ReportDto report = reportService.generateYearlyReport(year); //writer.println() possibly throws IOException

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        reportService.writeCsvReport(report, response.getWriter()); //possibly throws IOException
    }
}
