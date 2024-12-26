package org.example.businesspack.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.entities.DataWork;
import org.example.businesspack.repositories.TableRepository;
import org.example.businesspack.repositories.impl.DataWorkRepositoryImpl;
import org.example.businesspack.services.DataWorkService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DataWorkServiceImpl implements DataWorkService {

    private final TableRepository<DataWork> accountRepository = new DataWorkRepositoryImpl();

    @Override
    public Long save(DataWorkDto entity) {
        DataWork accountSave = DataWorkDto.to(entity);
        Long id = null;
        try {
            id = accountRepository.save(accountSave);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    @Override
    public List<DataWorkDto> get() {
        List<DataWorkDto> models = new ArrayList<>();
        try {
            List<DataWork> accounts = accountRepository.get();
            if (accounts != null) {
                models = accounts.stream()
                        .map(DataWorkDto::of)
                        .collect(Collectors.toList());
            } else {
                return models;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return models;
    }

    @Override
    public void delete(DataWorkDto entity) {
        DataWork accountDelete = DataWorkDto.to(entity);
        try {
            accountRepository.delete(accountDelete);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Long update(DataWorkDto entity) {
        DataWork accountUpdate = DataWorkDto.to(entity);
        Long id = null;
        try {
            if (entity.getId() != null) {
                id = accountRepository.update(accountUpdate);
            } else {
                id = accountRepository.save(accountUpdate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

}
