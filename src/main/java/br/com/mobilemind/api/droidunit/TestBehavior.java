package br.com.mobilemind.api.droidunit;

import android.content.Context;
import java.util.List;

/**
 * Define o comportamento dos testes unitário
 *
 * @author Ricardo Bocchi
 *
 */
public interface TestBehavior {

    /**
     * Metodo que executa quando uma metodo de teste inicia
     */
    void setUp() throws Exception;

    /**
     * Metodo que executa quando uma classe de teste inicia
     */
    void setUpClass() throws Exception;

    /**
     * Metodo que executa quando uma metodo de teste finaliza
     */
    void tearDown() throws Exception;

    /**
     * Metodo que executa quando uma classe de teste finaliza
     */
    void tearDownClass() throws Exception;

    /**
     * Adiciona uma mensagem no test
     *
     * @param message
     */
    void say(String message);

    /**
     * Limpa lista de mensagens dos testes
     * 
     */
    void clearMessages();

    /**
     * Recupera lista de mensagens dos testes
     * 
     * @return 
     */
    List<String> getMessage();

    /**
     * Adiciona context no teste
     * 
     * @param context 
     */
    void setContext(Context context);

    /**
     * Recupera contexto dentro de um teste
     * 
     * @return 
     */
    Context getContext();
}
