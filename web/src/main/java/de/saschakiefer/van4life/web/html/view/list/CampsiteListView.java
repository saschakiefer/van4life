package de.saschakiefer.van4life.web.html.view.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.saschakiefer.van4life.application.service.CampsiteService;
import de.saschakiefer.van4life.domain.entity.Campsite;
import de.saschakiefer.van4life.web.html.view.MainLayout;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Route(value = "campsites", layout = MainLayout.class)
@PageTitle("Campsites")
public class CampsiteListView extends VerticalLayout {
    // @Autowired does not work here
    // In Spring Autowired fields are not available in constructors as field injection is done after constructor
    final CampsiteService campsiteService;

    Grid<Campsite> grid = new Grid<>(Campsite.class);

    public CampsiteListView(CampsiteService campsiteService) {
        this.campsiteService = campsiteService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        FlexLayout content = new FlexLayout(grid);
        content.setFlexGrow(2, grid);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(content);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("campsite-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(grid::removeColumn);
        grid.addColumn("name").setHeader("Name");
        grid.addColumn("address.street").setHeader("Strasse");
        grid.addColumn("address.zip").setHeader("PLZ");
        grid.addColumn("address.city").setHeader("Ort");
        grid.addColumn("address.country").setHeader("Land");
        grid.addColumn("homepage").setHeader("Homepage");
        grid.addColumn("comment").setHeader("Kommentar");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        grid.setItems(campsiteService.findAllCampsites());
    }

}
