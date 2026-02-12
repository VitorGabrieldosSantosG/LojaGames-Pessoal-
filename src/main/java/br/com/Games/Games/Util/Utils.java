package br.com.Games.Games.Util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    //source, é o objeto vindo da requisição
    //target, é o objeto já existente no banco de dados
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source){

        //Cria um beenwrapper, que correponde a "empacotar" um objeto para que a gente possa utilizar métodos úteis (método abaixo)
        final BeanWrapper src = new BeanWrapperImpl(source);

        //Pega as propriedades do objeto
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        //Loop for que é responsável por adicionar em uma lista todas as propriedades que vieram nulas na requisição, ou seja, facilitando para que o usuário não precise inserir todas as propriedades completas
        for(PropertyDescriptor pd:pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

        //Cria um array de String com o tamanho da quantidade de itens vazio e depois retorna a lista de emptyNames em foirmato de array do tamanho e tipo do result.
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
