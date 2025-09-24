function confirmarExclusao(event) {
    if (!confirm('Você tem certeza que deseja excluir este item?')) {
        event.preventDefault();
    }
}