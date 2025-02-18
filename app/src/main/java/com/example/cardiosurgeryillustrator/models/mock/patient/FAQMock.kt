package com.example.cardiosurgeryillustrator.models.mock.patient

import com.example.cardiosurgeryillustrator.models.patient.faq.QuestionFAQ

val mockFAQ = listOf(
    QuestionFAQ(
        id = "1",
        question = "O que é um FAQ",
        answer = "FAQ significa Perguntas Frequentes."
    ),
    QuestionFAQ(
        id = "3",
        question = "É possível editar uma pergunta existente?",
        answer = "Sim, clique no ícone de edição ao lado da pergunta que deseja alterar."
    ),
    QuestionFAQ(
        id = "4",
        question = "Como excluir uma pergunta do FAQ?",
        answer = "Clique no ícone de lixeira ao lado da pergunta que deseja remover."
    ),
    QuestionFAQ(
        id = "5",
        question = "Qual é o limite de perguntas no FAQ?",
        answer = "Não há um limite definido, você pode adicionar quantas perguntas forem necessárias."
    ),
    QuestionFAQ(
        id = "6",
        question = "Os usuários podem visualizar o FAQ?",
        answer = "Sim, os usuários podem acessar as perguntas e respostas cadastradas no FAQ."
    ),
    QuestionFAQ(
        id = "7",
        question = "Como faço para salvar alterações no FAQ?",
        answer = "Ao editar ou adicionar uma pergunta, clique no botão 'Salvar' para aplicar as alterações."
    ),
    QuestionFAQ(
        id = "8",
        question = "O FAQ suporta formatação de texto?",
        answer = "No momento, o FAQ suporta apenas texto simples, sem formatação avançada."
    ),
    QuestionFAQ(
        id = "9",
        question = "Posso exportar o FAQ?",
        answer = "Essa funcionalidade está em desenvolvimento e estará disponível em uma futura atualização."
    ),
    QuestionFAQ(
        id = "10",
        question = "Como faço para buscar perguntas específicas no FAQ?",
        answer = "Use a barra de pesquisa na parte superior da tela para encontrar perguntas rapidamente."
    )
)