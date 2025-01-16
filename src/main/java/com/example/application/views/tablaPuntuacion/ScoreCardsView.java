package com.example.application.views.tablaPuntuacion;

import com.example.application.models.scoreCardModel;
import com.example.application.models.studentModel;
import com.example.application.services.scoreCardService;
import com.example.application.services.studentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.*;

@PageTitle("Tabla de puntuaciones")
@Route("ScoreCard")
@Menu(order = 1, icon = LineAwesomeIconUrl.FILE)
@JavaScript("./D3/chart2.js")
public class ScoreCardsView extends VerticalLayout {

    public ScoreCardsView(scoreCardService scoreCardService, studentService studentService) throws JsonProcessingException {
        setSpacing(false);

        H2 header = new H2("Puntuaciones");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        //Formulario de ingreso
        Button save = new Button("Guardar");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        ComboBox<studentModel> studentCmb = new ComboBox<>("Estudiante");
        studentCmb.setItems(studentService.getAllStudents());
        studentCmb.setItemLabelGenerator(studentModel::getName);
        TextField subject_name = new TextField("Nombre Curso");
        TextField total_marks = new TextField("Puntuacion Total");
        TextField marks_obtained = new TextField("Puntuación Obtenida");

        save.addClickListener(clickEvent -> {
            scoreCardModel scoreCard = new scoreCardModel();
            scoreCard.setSubject_name(subject_name.getValue());
            scoreCard.setTotal_marks(Double.parseDouble(total_marks.getValue()));
            scoreCard.setMarks_obtained(Double.parseDouble(marks_obtained.getValue()));
            scoreCard.setStudent(studentCmb.getValue());

            if (studentCmb.getValue() == null) {
                Notification.show("Por favor, selecciona un estudiante.");
                return;
            }


            try{
                scoreCardService.saveScoreCard(scoreCard);
                subject_name.clear();
                total_marks.clear();
                marks_obtained.clear();
                studentCmb.clear();
                Notification.show("Guardado Exitoso");
            }catch (Exception e){
                Notification.show("Error: " + e.getMessage());
            }
        });
        FormLayout formLayout = new FormLayout();
        formLayout.add(
                subject_name, total_marks,
                marks_obtained,
                studentCmb,
                save
        );

        Div formContainer = new Div();
        formContainer.add(formLayout);
        formContainer.getStyle().set("margin-bottom", "30px");
        add(formContainer);



        Button button = new Button("Descargar Informe");
        button.addClickListener(clickEvent -> {
             System.out.println("Clicked");
             UI.getCurrent().getPage().open("http://localhost:58158/api/report");
        });
        add(button);

        Grid<scoreCardModel> grid = new Grid<>(scoreCardModel.class, false);
//        grid.setColumns();
        grid.addColumn(scoreCardModel::getId).setHeader("ID");
        grid.addColumn(scoreCardModel::getSubject_name).setHeader("Asignatura");
        grid.addColumn(scoreCardModel::getTotal_marks).setHeader("Total");
        grid.addColumn(scoreCardModel::getMarks_obtained).setHeader("Obtenido");
        grid.addColumn(scoreCardModel -> scoreCardModel.getStudent().getName()).setHeader("Nombre del Estudiante");
        List<scoreCardModel> scoreCards = scoreCardService.getAllScoreCards();
        grid.setItems(scoreCards);
        grid.setMaxHeight("300px");
        add(grid);


        Div chartDiv = new Div();
        chartDiv.setId("chart");
        chartDiv.setWidth("500px");
        chartDiv.setHeight("300px");
        add(chartDiv);

        // Obtener datos y ejecutar renderChart
        List<Map<String,Object>> data = scoreCardService.getHighestScoresByStudent();
        ObjectMapper mapper = new ObjectMapper();
        String dataJson = mapper.writeValueAsString(data);
        UI.getCurrent().getPage().executeJs("window.renderChart(JSON.parse($0))", dataJson);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
