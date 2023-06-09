package br.com.alura.forum.repository

import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RespostaRepository: JpaRepository<Resposta, Long> {

    //manual
  // @Query("SELECT new br.com.alura.forum.model.Resposta(r.mensagem,r.autor_id,r.topico_id,r.data_criacao,r.solucao) FROM resposta r WHERE r.topico_id = :topico_id")
  // @Query("SELECT new br.com.alura.forum.dto.RespostaView(r.id,r.mensagem,r.autor_id,r.topico_id,r.data_criacao,r.solucao) FROM resposta r WHERE r.topico_id = :topico_id")
  //  fun findByTopicoId(topico_id: Long):List<Resposta>

    //automatico
    fun findByTopicoId(topicoId: Long):List<Resposta>;

}