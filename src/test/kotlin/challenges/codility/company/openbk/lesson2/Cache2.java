package challenges.codility.company.openbk.lesson2;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CachedArticlesService implements ArticlesService {
//
//  private ArticlesRepository articlesRepository;
//
//  @Autowired
//  public CachedArticlesService(ArticlesRepository articlesRepository) {
//    this.articlesRepository = articlesRepository;
//  }
//
//  @Override
//  @Cacheable(value = "articles", key = "#articleId")
//  public Article getArticle(Long articleId) {
//    return articlesRepository.get(articleId);
//  }
//
//  @Override
//  @CacheEvict(value = "articles", key = "#articleId")
//  public void removeArticle(Long articleId) {
//    articlesRepository.remove(articleId);
//  }
//
//  @Override
//  public void saveArticle(Article article) {
//    articlesRepository.save(article);
//  }
//
//  @Override
//  public Article updateLikes(Long articleId, int likes) {
//    Article article = articlesRepository.updateLikes(articleId, likes);
//    if (article != null) {
//      return article;
//    } else {
//      // If the article was not found in the database, remove it from the cache
//      removeArticle(articleId);
//      return null;
//    }
//  }
//}