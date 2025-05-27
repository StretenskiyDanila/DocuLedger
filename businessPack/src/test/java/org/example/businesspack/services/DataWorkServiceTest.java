package org.example.businesspack.services;

import org.example.businesspack.dto.DataWorkDto;
import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.dto.UserDataDto;
import org.example.businesspack.repositories.DataWorkRepository;
import org.example.businesspack.services.impl.DataWorkServiceImpl;
import org.example.businesspack.utils.Builder;
import org.jooq.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.List;

import static org.example.businesspack.bd.Tables.DATA_WORK;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataWorkServiceTest {

    private final DataWorkService service = new DataWorkServiceImpl();
    private final DataWorkRepository repository = mock(DataWorkRepository.class);
    private static final MockedStatic<Builder> mockBuilder = Mockito.mockStatic(Builder.class);

    @BeforeAll
    static void setUp() {
        mockBuilder.when(() -> Builder.to(any(), any(DataWorkDto.class))).thenReturn(null);
        mockBuilder.when(() -> Builder.to(any(), any(PersonDto.class))).thenReturn(null);
        mockBuilder.when(() -> Builder.to(any(), any(UserDataDto.class))).thenReturn(null);
    }

    @AfterAll
    static void tearDown() {
        mockBuilder.close();
    }

    @ParameterizedTest
    @MethodSource("provideCorrectDataWorkDto")
    void test_saveData_withCorrectDto(DataWorkDto dto) {
        when(repository.save(any(), any(), any())).thenReturn(1);
        service.save(dto);
    }

    @ParameterizedTest
    @MethodSource("provideGetDataCorrectDto")
    void test_getData_withCorrectDto(Condition condition) {
        when(repository.get(any(), any())).thenReturn(List.of(DataWorkDto.builder().build()));
        repository.get(null, condition);
    }

    @ParameterizedTest
    @MethodSource("provideCorrectDataWorkDto")
    void test_deleteData_withCorrectDto(DataWorkDto dto) {
        Mockito.doNothing().when(repository).delete(any(), any());
        repository.delete(null, DATA_WORK.ID.eq(dto.getIdParameter()));
    }

    @ParameterizedTest
    @MethodSource("provideDeleteAllByTabCorrectDto")
    void test_deleteAllByTab_withCorrectDto(String tab) {
        Mockito.doNothing().when(repository).delete(any(), any());
        repository.delete(null, DATA_WORK.TAB.eq(tab));
    }

    @ParameterizedTest
    @MethodSource("provideCorrectDataWorkDto")
    void test_updateData_withCorrectDto(DataWorkDto dto) {
        when(repository.update(any(), any(), any())).thenReturn(1);
        repository.update(null, null, DATA_WORK.ID.eq(dto.getIdParameter()));
    }

    private static DataWorkDto[] provideCorrectDataWorkDto() {
        return new DataWorkDto[]{
            new DataWorkDto("act"),
            new DataWorkDto("account"),
            new DataWorkDto("doc")};
    }

    private static Condition[] provideGetDataCorrectDto() {
        return new Condition[]{
                DATA_WORK.TAB.eq("act"),
                DATA_WORK.TAB.ge("account")};
    }

    private static String[] provideDeleteAllByTabCorrectDto() {
        return new String[]{"act", "account", "doc"};
    }

}