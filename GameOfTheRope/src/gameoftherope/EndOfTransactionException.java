/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope;

/**
 * Descrição geral:
 *       definição de uma excepção que sinaliza o fim de transacção num modelo de comunicação cliente-sevidor.
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 * @author Bernardo Ferreira <bernardomrfereira@ua.pt>
 */
public class EndOfTransactionException extends Exception {
   /**
    *  Variável interna
    */
   private Object lastAnswer = null;                  // última resposta do serviço

   /**
    *  Construtor de variáveis
     * @param lastAnswer
    */
   public EndOfTransactionException (Object lastAnswer)
   {
      super ();
      this.lastAnswer = lastAnswer;
   }

   /**
    *  Extracção da última resposta do serviço
     * @return 
    */
   public Object getLastAnswer ()
   {
      return (lastAnswer);
   }
}
