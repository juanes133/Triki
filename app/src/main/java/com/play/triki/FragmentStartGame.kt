package com.play.triki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.play.triki.databinding.FragmentStartGameBinding

class FragmentStartGame : Fragment() {

    private lateinit var binding: FragmentStartGameBinding
    private var cells = mutableMapOf<Int, String>()
    private var isX = true
    private var winner: String = ""
    private val totalCell = 9
    private val x = "X"
    private val o = "O"
    private val combinations: Array<IntArray> = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStartGameBinding.inflate(layoutInflater)
        binding.btn1.setOnClickListener {
            buttonSelected(0, binding.btn1)
        }
        binding.btn2.setOnClickListener {
            buttonSelected(1, binding.btn2)
        }
        binding.btn3.setOnClickListener {
            buttonSelected(2, binding.btn3)
        }
        binding.btn4.setOnClickListener {
            buttonSelected(3, binding.btn4)
        }
        binding.btn5.setOnClickListener {
            buttonSelected(4, binding.btn5)
        }
        binding.btn6.setOnClickListener {
            buttonSelected(5, binding.btn6)
        }
        binding.btn7.setOnClickListener {
            buttonSelected(6, binding.btn7)
        }
        binding.btn8.setOnClickListener {
            buttonSelected(7, binding.btn8)
        }
        binding.btn9.setOnClickListener {
            buttonSelected(8, binding.btn9)
        }
        binding.btnReset.setOnClickListener {
            newGame()
        }
        newGame()
        return binding.root
    }

    private fun buttonSelected(index: Int, button: Button) {
        playGame(index)
        button.text = cells[index]
        button.isEnabled = false
        checkWinner()
        update()
    }

    private fun checkWinner() {
        if (cells.isNotEmpty()) {
            for (combination in combinations) {
                val (a, b, c) = combination
                if (cells[a] != null && cells[a] == cells[b] && cells[a] == cells[c]) {
                    this.winner = cells[a].toString()
                }
            }
        }
    }

    private fun update() {
        when {
            winner.isNotEmpty() -> {
                binding.txtResult.text = getString(R.string.winner)
                binding.txtResult.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.green))
                binding.btn1.isEnabled = false
                binding.btn2.isEnabled = false
                binding.btn3.isEnabled = false
                binding.btn4.isEnabled = false
                binding.btn5.isEnabled = false
                binding.btn6.isEnabled = false
                binding.btn7.isEnabled = false
                binding.btn8.isEnabled = false
                binding.btn9.isEnabled = false

            }
            cells.size == totalCell -> {
                binding.txtResult.text = getString(R.string.tie)
                binding.txtResult.setTextColor(ContextCompat.getColor(requireContext(),
                    R.color.black))
            }
            else -> {
                binding.txtResult.text = getString(R.string.next_player, if (isX) x else o)

            }
        }
    }

    private fun playGame(index: Int) {
        if (winner.isNotEmpty()) {
            Toast.makeText(context, "Juego finalizado", Toast.LENGTH_SHORT).show()
            return
        }
        when {
            isX -> {
                cells[index] = x
            }
            else -> cells[index] = o
        }
        isX = !isX
    }

    private fun resetButton() {
        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
        binding.btn9.text = ""
        binding.btn1.isEnabled = true
        binding.btn2.isEnabled = true
        binding.btn3.isEnabled = true
        binding.btn4.isEnabled = true
        binding.btn5.isEnabled = true
        binding.btn6.isEnabled = true
        binding.btn7.isEnabled = true
        binding.btn8.isEnabled = true
        binding.btn9.isEnabled = true
    }

    private fun newGame() {
        cells = mutableMapOf()
        isX = true
        winner = ""
        binding.txtResult.text = getString(R.string.next_player, x)
        binding.txtResult.setTextColor(ContextCompat.getColor(requireContext(),
            R.color.white))
        resetButton()
    }
}