package utfpr.crud.forms;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import utfpr.crud.ConfirmationDialog;
import utfpr.crud.MainView;
import utfpr.crud.model.Carro;
import utfpr.crud.model.Marca;
import utfpr.crud.model.Opcional;
import utfpr.crud.repository.CarroRepository;
import utfpr.crud.repository.MarcaRepository;
import utfpr.crud.repository.OpcionalRepository;

public class CarroForm extends FormLayout {
    private TextField modelo = new TextField("Modelo: ");
    private ComboBox<Marca> marca = new ComboBox<>("Marca: ");
    private IntegerField ano = new IntegerField("Ano: ");
    private RadioButtonGroup<String> cor = new RadioButtonGroup<>();
    private ComboBox<Opcional> opcional = new ComboBox<>("Opcional: ");

    private Button salvarButton = new Button("Salvar");
    private Button excluirButton = new Button("Excluir");
    
    private MainView mainView;
    private ConfirmationDialog confirmationDialog = new ConfirmationDialog();
    
    private Binder<Carro> binder = new Binder<>(Carro.class);
    private CarroRepository carroRepository;
    private OpcionalRepository opcionalRepository;
    private MarcaRepository marcaRepository;
        
    public CarroForm(MainView mainView, CarroRepository carroRepository,
                       OpcionalRepository opcionalRepository, MarcaRepository marcaRepository) {
        this.mainView = mainView;
        this.carroRepository = carroRepository;
        this.opcionalRepository = opcionalRepository;
        this.marcaRepository = marcaRepository;
        
        binder.bindInstanceFields(this);

        configuraCampos();

        HorizontalLayout btns = new HorizontalLayout(salvarButton, excluirButton);
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(event -> salvar());
        excluirButton.addClickListener(event -> excluir());

        add(modelo, marca, ano, opcional, cor, btns);
    }

    private void configuraCampos() {
        modelo.setRequired(true);

        marca.setItems(marcaRepository.findAll());
        marca.setRequired(true);

        cor.setRequired(true);
        cor.setLabel("Cor: ");
        cor.setItems("Vermelho", "Branco", "Preto", "Amarelo", "Azul");

        ano.setMin(1900);

        opcional.setItems(opcionalRepository.findAll());
        opcional.setRequired(true);
    }

    public void setCarro(Carro carro) {
        binder.setBean(carro);

        if (carro == null) {
            setVisible(false);
        } else {
            setVisible(true);
            setValorCampoCor(carro.getCor());
            if (binder.getBean().isPersisted()) {
                excluirButton.setVisible(true);
            } else {
                excluirButton.setVisible(false);
            }
            modelo.focus();
        }
    }

    private void setValorCampoCor(String corDoCarro) {
        cor.setValue(corDoCarro);
    }

    private void salvar() {
        Carro carro = binder.getBean();
        carroRepository.save(carro);
        mainView.atualizarLista();
        setCarro(null);
    }
    
    private void excluir() {
        Carro carro = binder.getBean();
        confirmationDialog.setQuestion("Deseja realmente excluir o carro '"+ carro.getModelo() + "'?");
        confirmationDialog.open();
        confirmationDialog.addConfirmationListener(evt -> {
            carroRepository.delete(carro);
            mainView.atualizarLista();
            setCarro(null);
        });
    }
    
}
