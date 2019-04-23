package com.mobiquityinc.packer;

import org.junit.Test;
import packer.Packer;
import packer.domain.Item;
import packer.domain.exceptions.APIException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class PackerTest {

    @Test
    public void testHappyFlow() throws APIException {
        //given
        final String filePath = "src/test/resources/packages.txt";
        //when
        String result =  Packer.pack(filePath);
        //then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("4\n- \n2, 7\n8, 9\n");
    }

    @Test
    public void testExceptionCase(){
        assertThatExceptionOfType(APIException.class)
                .isThrownBy(() -> Packer.pack("incorrectpath"));
    }

    @Test
    public void testProcess() throws APIException {
        //given
        final String line = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        //when
        List<Item> items =  Packer.process(line);
        //then
        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getIndex()).isEqualTo(4);

    }

}
