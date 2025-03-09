package org.example.businesspack.window.models.combo_box;

import static org.example.businesspack.bd.Tables.PERSON;

import java.util.List;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.enums.PersonRole;
import org.example.businesspack.services.PersonService;

import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class PersonComboBox extends ComboBoxManager<PersonDto> {

    private final PersonRole role;

    public PersonComboBox(ComboBox<PersonDto> comboBox, PersonService service, PersonRole role, String tabName) {
        super(comboBox, service, tabName);
        this.role = role;
    }

    @Override
    protected StringConverter<PersonDto> getConverter() {
        return new StringConverter<>() {
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
                                .tab(tabName)
                                .build());
            }
        };
    }

    @Override
    protected List<PersonDto> get() {
        return service.get(PERSON.ROLE.eq(role.getName())
                .and(PERSON.TAB.eq(tabName)));
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
        return newValue.isEmpty() || newValue == null ? true : item.getName().toLowerCase().contains(newValue.toLowerCase());
    }

}
