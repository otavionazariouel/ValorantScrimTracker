package uel.br.valorantscrimtracker.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String tratarRuntimeException(RuntimeException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        return "redirect:/jogadores";
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public String tratarViolacaoIntegridade(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", "Não é possível excluir este registro pois ele possui vínculos ativos no sistema.");
        return "redirect:/jogadores";
    }

    @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
    public String tratarErroTipoParametro(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", "Identificador inválido fornecido na requisição.");
        return "redirect:/partidas";
    }
}