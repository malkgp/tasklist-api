package br.com.okfx.tasklist.api.event.listener;

import br.com.okfx.tasklist.api.event.RecursoCriadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener {

    @EventListener
    public void handleResponseLocationHeader(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Long codigoRecurso = event.getCodigoRecurso();

        URI locationURI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(codigoRecurso)
                .toUri();

        response.addHeader("Location", locationURI.toASCIIString());
    }

}
