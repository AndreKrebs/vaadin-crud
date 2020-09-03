package utfpr.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import utfpr.crud.forms.CarroForm;
import utfpr.crud.model.Carro;
import utfpr.crud.repository.CarroRepository;
import utfpr.crud.repository.MarcaRepository;
import utfpr.crud.repository.OpcionalRepository;


@Route
@PWA(name = "CRUD",
     shortName = "CRUD App",
     description = "Exemplo de crud",
     enableInstallPrompt = false)
public class MainView extends VerticalLayout {

    private final CarroRepository carroRepository;
    private final MarcaRepository marcaRepository;
    private final OpcionalRepository opcionalRepository;
    private Grid<Carro> grid = new Grid<>(Carro.class);
    Button novoBtn = new Button("Novo carro");
    
    private CarroForm form;
    
    public MainView(CarroRepository carroRepository, MarcaRepository marcaRepository,
                    OpcionalRepository opcionalRepository) {

        this.carroRepository = carroRepository;
        this.marcaRepository = marcaRepository;
        this.opcionalRepository = opcionalRepository;


        form = new CarroForm(this, carroRepository, opcionalRepository, marcaRepository);
        form.setCarro(null);
        
        grid.setColumns("modelo", "ano", "cor");
        grid.setSizeFull();
        atualizarLista();
        grid.asSingleSelect().addValueChangeListener(e -> selecionar());

        novoBtn.addClickListener(e -> adicionarNovo());

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(new HorizontalLayout(novoBtn), mainContent);
        setSizeFull();
    }

    public void atualizarLista() {
        grid.setItems(carroRepository.findAll());
    }

    public void selecionar() {
        form.setCarro(grid.asSingleSelect().getValue());
    }

    private void adicionarNovo() {
        grid.asSingleSelect().clear();
        form.setCarro(new Carro());
    }

}
