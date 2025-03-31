package com.onlineLibrary.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlineLibrary.DTO.BooksPageQueryDTO;
import com.onlineLibrary.DTO.PublicationDTO;
import com.onlineLibrary.entity.Books;
import com.onlineLibrary.result.PageResult;
import com.onlineLibrary.result.Result;
import com.onlineLibrary.service.HomePageService;
import com.onlineLibrary.service.PublicationPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.Year;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private HomePageService homePageService;
    @Autowired
    private PublicationPageService publicationPageService;
    @Autowired
    private RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 条件查询书籍并分页展示
     * @param booksPageQueryDTO
     * @return
     */
    //条件查询 非homepage多功能查询
    @GetMapping("/page")
    @CrossOrigin()
    public Result<PageResult> pageQuery(BooksPageQueryDTO booksPageQueryDTO){
        PageResult pageResult = homePageService.pageQuery(booksPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 评分最高书籍/新书籍展示
     * @return
     */
    @GetMapping()
    @CrossOrigin()
    public Result<Map<String,List<Books>>> topBooksQuery(){

        List<Books> topBooks = homePageService.topbooksQuery();
        List<Books> newBooks = homePageService.newbooksQuery();

        Map<String, List<Books>> result = new HashMap<>();
        result.put("topBooks", topBooks);
        result.put("newBooks", newBooks);

        return Result.success(result);
    }

    /*
    @GetMapping("/recent-papers")
    public Result<?> getRecentPapers() {

        String url = "https://api.openalex.org/works"
                + "?sort=cited_by_count:desc"
                + "&select=title,authorships,publication_year,cited_by_count,doi"
                + "&per-page=10";


        // 调用 OpenAlex API
        Map response = restTemplate.getForObject(url, Map.class);
        //System.out.println("OpenAlex API Response: " + response);
        if (response != null) {
            return Result.success(response);
        } else {
            return Result.error("获取论文数据失败");
        }

    }
    */

    @GetMapping("/recent-papers")
    public Result<List<PublicationDTO>> getRecentPapers() {
        String url = "https://api.openalex.org/works"
                + "?sort=cited_by_count:desc"
                + "&select=title,authorships,publication_year,cited_by_count,doi"
                + "&per-page=10";

        // 调用 OpenAlex API，直接获取 String 类型的响应
        String json = restTemplate.getForObject(url, String.class);
        System.out.println("OpenAlex API Response: " + json);

        List<PublicationDTO> publicationDTOList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results");
            JsonNode metaNode = rootNode.get("meta");

            int page = metaNode.get("page").asInt();
            int pageSize = metaNode.get("per_page").asInt();

            if (resultsNode != null && resultsNode.isArray()) {
                for (JsonNode resultNode : resultsNode) {
                    String title = resultNode.get("title").asText();
                    String doi = resultNode.get("doi").asText();
                    int citedCount = resultNode.get("cited_by_count").asInt();
                    int publicationYearInt = resultNode.get("publication_year").asInt();
                    Year publicationYear = Year.of(publicationYearInt);

                    StringBuilder authorNames = new StringBuilder();
                    JsonNode authorshipsNode = resultNode.get("authorships");
                    if (authorshipsNode != null && authorshipsNode.isArray()) {
                        for (JsonNode authorshipNode : authorshipsNode) {
                            JsonNode authorNode = authorshipNode.get("author");
                            if (authorNode != null) {
                                String displayName = authorNode.get("display_name").asText();
                                if (authorNames.length() > 0) {
                                    authorNames.append(", ");
                                }
                                authorNames.append(displayName);
                            }
                        }
                    }
                    String author = authorNames.toString();

                    PublicationDTO dto = new PublicationDTO();
                    dto.setTitle(title);
                    dto.setDoi(doi);
                    dto.setCitedCount(citedCount);
                    dto.setPublicationYear(publicationYear);
                    dto.setAuthor(author);
                    dto.setPage(page);
                    dto.setPageSize(pageSize);

                    publicationDTOList.add(dto);
                    publicationPageService.savePublication(dto);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to parse API response");
        }

        return Result.success(publicationDTOList);
    }

}