package challenges.codility.company.openbk.lesson3;

import org.springframework.web.bind.annotation.*;

@RestController
class TaskController {
//  private static Logger log = Logger.getLogger("Solution");
//  // log.info("You can use 'log' for debug messages");
//  private TaskRepository taskRepository;
//
//  public TaskController(TaskRepository taskRepository) {
//    this.taskRepository = taskRepository;
//  }
//
//  @PutMapping("/tasks/{id}")
//  public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody RequestTask requestTask) {
//    if (!taskRepository.existsById(id)){
//      return newNotFoundResponse();
//    }
//
//    if (requestTask.getDescription() == null) {
//      return newBadRequestResponse();
//    }
//
//    Task task = taskRepository.findById(id).get();
//    task.setDescription(requestTask.getDescription());
//    task.setPriority(requestTask.getPriority());
//    taskRepository.save(task);
//    return newOkResponse(task);
//  }
//
//  public ResponseEntity<Object> newBadRequestResponse() {
//    return new ResponseEntity<Object>(new HttpRes"{\"message\": \"Task description is required\"\"status\": 400}", HttpStatus.BAD_REQUEST);
//  }
//
//  public ResponseEntity<Object> newNotFoundResponse() {
//    return new ResponseEntity<Object>("{ \"message\": \"Cannot find task with given id\" \"status\": 404}", HttpStatus.NOT_FOUND);
//  }
//
//  public ResponseEntity<Object> newOkResponse(Task task) {
//    return new ResponseEntity<Object>(formatMessage(task.getDescription()), HttpStatus.Ok);
//  }
//
//  private String formatMessage(String message) {
//    return String.format("{\"message\": \"%s\"\"status\": 200}", message);
//  }
}

