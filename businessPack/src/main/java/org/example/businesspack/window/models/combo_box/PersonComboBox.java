package org.example.businesspack.window.models.combo_box;

import java.util.List;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.enums.PersonRole;
import org.example.businesspack.services.Service;

import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class PersonComboBox extends ComboBoxManager<PersonDto> {

    // TODO Не работает занос новых имен в таблицу !!!

    private final PersonRole role;

    public PersonComboBox(ComboBox<PersonDto> comboBox, Service<PersonDto> service, PersonRole role) {
        super(comboBox, service);
        this.role = role;
        // setConfigurationComboBox();
    }

    @Override
    protected StringConverter<PersonDto> getConverter() {
        return new StringConverter<PersonDto>() {
            @Override
            public String toString(PersonDto person) {
                return person == null ? null : person.getName();
            }

            @Override
            public PersonDto fromString(String name) {
                return items.stream()
                        .filter(person -> person.getName().equals(name))
                        .findFirst()
                        .orElse(PersonDto.builder()
                                .name(name)
                                .role(role)
                                .build());
            }
        };
    }

    @Override
    protected List<PersonDto> get() {
        return service.get(role.getName());
    }

    @Override
    protected void updateEnterItem() {
        PersonDto item = selectedItem.orElse(PersonDto.builder()
                .name(comboBox.getEditor().getText())
                .role(role)
                .build());

        item.setId(service.update(item));
        items.add(item);
    }

    @Override
    protected boolean filteredItem(PersonDto item, String newValue) {
        return item.getName().toLowerCase().contains(newValue.toLowerCase());
    }

    // private void setConfigurationComboBox() {
    //     comboBox.setButtonCell(new ListCell<>() {
    //         @Override
    //         protected void updateItem(PersonDto item, boolean empty) {
    //             super.updateItem(item, empty);
    //             if (empty || item == null) {
    //                 setText(null);
    //             } else {
    //                 setText(item.getName());
    //             }
    //         }
    //     });

    //     comboBox.setCellFactory(param -> new ListCell<>() {
    //         @Override
    //         protected void updateItem(PersonDto item, boolean empty) {
    //             super.updateItem(item, empty);
    //             if (empty || item == null) {
    //                 setText(null);
    //             } else {
    //                 setText(item.getName());
    //             }
    //         }
    //     });
    // }

}
