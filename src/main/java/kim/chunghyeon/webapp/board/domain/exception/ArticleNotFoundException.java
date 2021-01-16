package kim.chunghyeon.webapp.board.domain.exception;

import kim.chunghyeon.webapp.common.domain.exception.DomainNotFoundException;

public class ArticleNotFoundException extends DomainNotFoundException {
    public ArticleNotFoundException(long id) {
        super("article #" + id + " has not been found.");
    }
}
