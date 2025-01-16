package com.example.application.views.estudiantes;

import com.example.application.models.studentModel;
import com.example.application.services.studentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import java.util.List;

@PageTitle("Estudiantes")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.FILE)
public class EstudiantesView extends VerticalLayout {

    public EstudiantesView(studentService studentService) {
        setSpacing(false);

        H2 header = new H2("Lista de Estudiantes");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);

        Button button = new Button("Guardar");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        TextField name = new TextField("Nombre");
        TextField year = new TextField("Año");
        TextField advisor = new TextField("Catedratico");
        TextField grading_period = new TextField("Periodo de Notas");
        button.addClickListener(clickEvent -> {
            System.out.println("Click");
            studentModel student = new studentModel();
            student.setName(name.getValue());
            student.setYear(year.getValue());
            student.setAdvisor(advisor.getValue());
            student.setGrading_period(grading_period.getValue());

            try{
                studentService.saveStudent(student);
                name.clear();
                year.clear();
                advisor.clear();
                grading_period.clear();
                Notification.show("Guardado Exitoso");
            }catch (Exception e){
                Notification.show("Error: " + e.getMessage());
            }
        });
        FormLayout formLayout = new FormLayout();
        formLayout.add(
                name, year,
                advisor,
                grading_period,
                button
        );
        // Stretch the username field over 2 columns
        formLayout.setColspan(advisor, 2);

        Div formContainer = new Div();
        formContainer.add(formLayout);
        formContainer.getStyle().set("margin-bottom", "30px");
        add(formContainer);


        Grid<studentModel> grid = new Grid<>(studentModel.class);
        grid.setColumns("roll_number", "name", "year", "advisor", "grading_period");
        grid.setWidth("99%");
        grid.setHeight("50%");
        List<studentModel> students = studentService.getAllStudents();
        grid.setItems(students);
        grid.setMaxHeight("300px");
        add(grid);


        setSizeFull();
        setJustifyContentMode(JustifyContentMode.START);
        setDefaultHorizontalComponentAlignment(Alignment.START);
        getStyle().set("text-align", "left");
    }

}
