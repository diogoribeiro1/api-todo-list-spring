package com.elixir.todolist.controller;

import java.util.List;

import com.elixir.todolist.model.Task;
import com.elixir.todolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@ApiOperation(value = "Criando uma nova tarefa")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Tarefa criada com sucesso"),
			@ApiResponse(code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")

	})

	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task) {
		return taskService.createTask(task);
	}

	@ApiOperation(value = "Listando todas as tarefas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefas listadas com sucesso"),
			@ApiResponse(code = 500, message = "Houve um erro ao listar as tarefas")

	})
	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public List<Task> getAllTasks() {
		return taskService.listAllTasks();
	}

	@ApiOperation(value = "Buscando uma tarefa pelo id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefa encontrada com sucesso"),
			@ApiResponse(code = 404, message = "Não foi encontrada nenhuma tarefa com esse id")

	})
	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id) {
		return taskService.findTaskById(id);
	}

	@ApiOperation(value = "Atualizando uma tarefa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tarefa atualizada com sucesso"),
			@ApiResponse(code = 404, message = "Nao foi possivel atualizar a tarefa - tarefa nao encontrada")

	})
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task) {

		return taskService.updateTaskById(task, id);
	}

	@ApiOperation(value = "Excluindo uma tarefa")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Tarefa excluida com sucesso"),
			@ApiResponse(code = 404, message = "Nao foi possivel excluir a tarefa - tarefa nao encontrada")

	})
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id) {
		return taskService.deleteById(id);
	}

}
